<template>
    <div class="control-section">
        <h5>Напредна претрага</h5>
        <div class="col-lg-12 querybuilder-control">
            <ejs-querybuilder ref="querybuilder" width="100%" :ruleChange="updateRule">
                <e-columns>
                    <e-column field='id' label='ИД' type='string' :operators="operatorsString" />
                    <e-column field='ime' label='Име' type='string' :operators="operatorsString" />
                    <e-column field='prezime' label='Презиме' type='string' :operators="operatorsString" />
                    <e-column field='boolean' label='Булеан' type='boolean' :values="values" :operators="operatorsBoolean" />
                    <e-column field='datum' label='Датум' type='date' format='dd/MM/yyyy' :operators="operatorsDate"/>
                </e-columns>
            <button @click="napredna" type="button" class="btn btn-primary ml-4 mt-1" data-dismiss="modal">Потврди</button>
            </ejs-querybuilder>
        </div>


    </div>
</template>

<script>
import axios from 'axios';
import xmljs from "xml-js";
import {mapState} from 'vuex'



    export default {
        name: "NaprednaPretraga",
        data() {
            return {
                values: ['true', 'false'],
                jsonContent: "",
                finalString: "",
                operatorsString: [
                    {value : 'startswith', key: 'Почиње са'},
                    {value : 'endswith', key: 'Завршава се са'},
                    {value : 'contains', key: 'Садржи'},
                    {value : 'equal', key: 'Једнако'},
                    {value : 'notequal', key: 'Различито'}
                ],
                operatorsNumber: [
                    {value : 'equal', key: 'Једнако'},
                    {value : 'notequal', key: 'Различито'},
                    {value : 'greaterthan', key: 'Веће'},
                    {value : 'lessthan', key: 'Мање'},
                ],
                operatorsDate: [
                    {value : 'equal', key: 'Једнако'},
                    {value : 'notequal', key: 'Различито'},
                    {value : 'greaterthan', key: 'Веће'},
                    {value : 'lessthan', key: 'Мање'},
                ],
                operatorsBoolean: [
                    {value : 'equal', key: 'Једнако'},
                    {value : 'notequal', key: 'Различито'}
                ],
                operatorsDict: {
                    'startswith': 'STRSTARTS',
                    'endswith' : 'STRENDS',
                    'contains': 'CONTAINS',
                    'equal': '=',
                    'notequal': '!=',
                    'greaterthan': '>',
                    'lessthan': '<'
                }
            }
        },
        computed: {
            ...mapState([
                'naprednaList',
            ]),

        },

        methods: {
            updateRule() {
                var validRule = this.$refs.querybuilder.ej2Instances.getValidRules(this.$refs.querybuilder.ej2Instances.rule);
                this.jsonContent = JSON.stringify(validRule, null, 4);
                this.finalString = "";
                this.jsonToString(validRule);
                // console.log(this.finalString);
                this.stringToSparql(this.finalString);
                console.log(this.finalString);
            },

            jsonToString(str) {
                this.recursiveJsonToString(str);
            },

            stringToSparql(str) {
                let arr = str.split(/AND|OR/);
                // console.log(arr);

                let finalArr = str.split(' ');
                // console.log(finalArr);

                let count = 0;
                for(let a of arr) {
                    let oneRule = a.trim().split(" ");
                    if(oneRule[0][0] === "(")
                        oneRule[0] = "(" + oneRule[0].substr(1);
                    // else oneRule[0] = oneRule[0];
                    oneRule[1] = this.operatorsDict[oneRule[1]];

                    if(count === 0) {
                        if(oneRule[1] === "STRSTARTS" || oneRule[1] === "STRENDS" || oneRule[1] === "CONTAINS") {
                            if(oneRule[0][0] === "(") {
                                finalArr[0] = "(( ?p = <http://www.ftn.uns.ac.rs/predicate/" + oneRule[0].slice(1) + "> && "
                                finalArr[0] += oneRule[1];
                                finalArr[1] = "(?o" + ", ";
                                // finalArr[2] = oneRule[0] + ", ";
                                finalArr[2] = '"' + oneRule[2] + '"' + "))"
                                if (oneRule[2].slice(-1) === ")") finalArr[2] += ")"
                            } else {
                                finalArr[0] = "( ?p = <http://www.ftn.uns.ac.rs/predicate/" + oneRule[0] + "> && "
                                finalArr[0] += oneRule[1];
                                // finalArr[1] = "(" + oneRule[0] + ", ";
                                finalArr[1] = "(" + "?o" + ", ";
                                finalArr[2] = '"' + oneRule[2] + '"' + "))"
                                
                                if (oneRule[2].slice(-1) === ")") finalArr[2] = '"' + oneRule[2].slice(0, -1) + '"' + ")))"
                                else finalArr[2] = '"' + oneRule[2] + '"' + "))"

                            }
                        } else {
                            finalArr[0] = "( ?p = <http://www.ftn.uns.ac.rs/predicate/" + oneRule[0] + "> && "
                            finalArr[0] += oneRule[0];
                            finalArr[1] = oneRule[1];
                            finalArr[2] = '"' + oneRule[2] + '")';
                        }
                    } else {
                        if(oneRule[1] === "STRSTARTS" || oneRule[1] === "STRENDS" || oneRule[1] === "CONTAINS") {
                            if (oneRule[0][0] === "(") {
                                finalArr[count * 4] = "(( ?p = <http://www.ftn.uns.ac.rs/predicate/" + oneRule[0].slice(1) + "> && "
                                finalArr[count * 4 + 0] += oneRule[1];
                                finalArr[count * 4 + 1] = "(?o" + ", ";
                                finalArr[count * 4 + 2] = '"' + oneRule[2] + '"' + "))"
                                if (oneRule[2].slice(-1) === ")") finalArr[count * 4 + 2] = '"' + oneRule[2].slice(0, -1) + '"' + ")))"
                                else finalArr[count * 4 + 2] = '"' + oneRule[2] + '"' + "))"
                            } else {
                                finalArr[count * 4] = "( ?p = <http://www.ftn.uns.ac.rs/predicate/" + oneRule[0] + "> && "

                                finalArr[count * 4 + 0] += oneRule[1];
                                finalArr[count * 4 + 1] = "(" + "?o" + ", ";
                                
                                // finalArr[count * 4 + 1] = "(" + oneRule[0] + ", ";
                                finalArr[count * 4 + 2] = '"' + oneRule[2] + '"' + "))"

                                if (oneRule[2].slice(-1) === ")") finalArr[count * 4 + 2] = '"' + oneRule[2].slice(0, -1) + '"' + ")))"
                                else finalArr[count * 4 + 2] = '"' + oneRule[2] + '"' + "))"                            }
                        } else {
                            finalArr[count * 4] = "( ?p = <http://www.ftn.uns.ac.rs/predicate/" + oneRule[0] + "> && "
                            finalArr[count * 4 + 0] += oneRule[0];
                            finalArr[count * 4 + 1] = oneRule[1];
                            finalArr[count * 4 + 2] = '"' + oneRule[2] + '")';
                        }
                    }
                    count += 1;
                }

                this.finalString = finalArr.join(" ");
                this.finalString = this.finalString.replace(/AND/g, "&&").replace(/OR/g, "||");
            },

            recursiveJsonToString(str) {
                let count = 0;
                for(let rule of str.rules) {
                    if(rule.label != null) {
                        this.finalString += rule.field + " " + rule.operator + " " + rule.value;
                    }
                    else {
                        this.finalString += "(";
                        this.recursiveJsonToString(rule, this.finalString);
                        this.finalString += ")";
                    }
                    if(count === str.rules.length-1) {
                        //this.finalString += " ";
                    } else {
                        if(str.condition === "and")
                            this.finalString += " AND ";
                        else this.finalString += " OR ";
                    }
                    count += 1;
                }
            },


            async napredna() {


                let response = await axios({
                    method: 'GET',
                    url: `http://localhost:8081/api/izvestaj/napredna`,
                    headers: {
                        'Content-Type': 'application/xml'
                    },
                    params: {'query': this.finalString }
                });

                this.$store.dispatch('setNaprednaList', {});


                let data = JSON.parse(xmljs.xml2json(response.data, {compact: true, spaces: 4}));
                console.log(data);

                let sparqlResults = await data["sparql"]?.["results"]?.["result"]
                if (!sparqlResults) return;
                let subjects = {}

                sparqlResults.forEach(result => {
                    let id = result.binding[0].uri._text;
                    if (!subjects[id]) subjects[id] = {}
                    
                    let preds = result.binding[1].uri._text.split("/")
                    let pred = preds[preds.length - 1]

                    let obj = result.binding[2].literal._text;

                    subjects[id][pred] = obj

                });

                this.$store.dispatch('setNaprednaList', subjects);


            }
        },
    }
</script>

<style scoped>

</style>