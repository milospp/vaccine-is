<template>
    <div class="control-section">
        <h5>Напредна претрага</h5>
        <div class="col-lg-12 querybuilder-control">
            <ejs-querybuilder ref="querybuilder" width="70%" :ruleChange="updateRule">
                <e-columns>
                    <e-column field='id' label='ИД' type='string' :operators="operatorsString" />
                    <e-column field='ime' label='Име' type='string' :operators="operatorsString" />
                    <e-column field='prezime' label='Презиме' type='string' :operators="operatorsString" />
                    <e-column field='boolean' label='Булеан' type='boolean' :values="values" :operators="operatorsBoolean" />
                    <e-column field='datum' label='Датум' type='date' format='dd/MM/yyyy' :operators="operatorsDate"/>
                </e-columns>
            </ejs-querybuilder>
        </div>
        <pre>{{jsonContent}}</pre>
    </div>
</template>

<script>
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
                    {equal : 'equal', key: 'Једнако'},
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
        methods: {
            updateRule() {
                var validRule = this.$refs.querybuilder.ej2Instances.getValidRules(this.$refs.querybuilder.ej2Instances.rule);
                this.jsonContent = JSON.stringify(validRule, null, 4);
                this.finalString = "";
                this.jsonToString(validRule);
                console.log(this.finalString);
                this.stringToSparql(this.finalString);
                console.log(this.finalString);
            },

            jsonToString(str) {
                this.recursiveJsonToString(str);
            },

            stringToSparql(str) {
                let arr = str.split(/AND|OR/);
                console.log(arr);

                let finalArr = str.split(' ');
                console.log(finalArr);

                let count = 0;
                for(let a of arr) {
                    let oneRule = a.trim().split(" ");
                    if(oneRule[0][0] === "(")
                        oneRule[0] = "(?" + oneRule[0].substr(1);
                    else oneRule[0] = "?" + oneRule[0];
                    oneRule[1] = this.operatorsDict[oneRule[1]];

                    if(count === 0) {
                        if(oneRule[1] === "STRSTARTS" || oneRule[1] === "STRENDS" || oneRule[1] === "CONTAINS") {
                            if(oneRule[0][0] === "(") {
                                finalArr[0] = "(" + oneRule[1];
                                finalArr[1] = oneRule[0] + ", ";
                                finalArr[2] = '"' + oneRule[2] + '"' + ")"
                            } else {
                                finalArr[0] = oneRule[1];
                                finalArr[1] = "(" + oneRule[0] + ", ";
                                finalArr[2] = '"' + oneRule[2] + '"' + ")"
                            }
                        } else {
                            finalArr[0] = oneRule[0];
                            finalArr[1] = oneRule[1];
                            finalArr[2] = '"' + oneRule[2] + '"';
                        }
                    } else {
                        if(oneRule[1] === "STRSTARTS" || oneRule[1] === "STRENDS" || oneRule[1] === "CONTAINS") {
                            if (oneRule[0][0] === "(") {
                                finalArr[count * 4] = "(" + oneRule[1];
                                finalArr[count * 4 + 1] = oneRule[0] + ", ";
                                finalArr[count * 4 + 2] = '"' + oneRule[2] + '"' + ")"
                            } else {
                                finalArr[count * 4] = oneRule[1];
                                finalArr[count * 4 + 1] = "(" + oneRule[0] + ", ";
                                finalArr[count * 4 + 2] = '"' + oneRule[2] + '"' + ")"
                            }
                        } else {
                            finalArr[count * 4] = oneRule[0];
                            finalArr[count * 4 + 1] = oneRule[1];
                            finalArr[count * 4 + 2] = '"' + oneRule[2] + '"';
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
            }
        },
    }
</script>

<style scoped>

</style>