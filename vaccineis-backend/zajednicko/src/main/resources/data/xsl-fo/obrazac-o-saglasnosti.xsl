<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:saglasnost="http://www.ftn.uns.ac.rs/obrazac-saglasnosti"
                xmlns:zaj="http://www.ftn.uns.ac.rs/zajednicka"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="saglasnost-page" page-width="595px" page-height="950px">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="saglasnost-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block margin-top="-30px">
                        <fo:inline-container inline-progression-dimension="65%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="17px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                САГЛАСНОСТ ЗА СПРОВОЂЕЊЕ ПРЕПОРУЧЕНЕ ИМУНИЗАЦИЈЕ
                            </fo:block >
                            <fo:block text-align="left" font-family="Arial" font-size="14.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                (попуњава пацијент)
                            </fo:block >
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="15%" margin-left="10px">
                            <fo:block>
                                <fo:external-graphic src='url("/home/dalibor/Desktop/vaccine-is/vaccineis-backend/zajednicko/src/main/resources/static/images/Screenshot from 2022-02-08 11-18-10.png")' height="20px" content-width="0.8in" />
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="22%" margin-left="5px">
                            <fo:block text-align="left" font-family="Arial" font-size="8.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                ИНСТИТУТ ЗА ЈАВНО ЗДРАВЉЕ СРБИЈЕ „Др Милан Јовановић Батут“
                            </fo:block >
                        </fo:inline-container>
                    </fo:block>

                    <xsl:choose>
                        <xsl:when test="//saglasnost:drzavljanstvo/@vrstaDrzavljanstva = 'srpsko'">
                            <fo:block margin-top="20px" text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                Држављанство:  <fo:inline font-weight="bold">1) Република Србија | ЈМБГ : </fo:inline>
                                <fo:inline font-weight="bold"> <xsl:value-of select="//saglasnost:drzavljanstvo/saglasnost:jmbg"/> </fo:inline>
                            </fo:block>
                            <fo:block margin-top="20px" text-align="left" font-family="Arial" font-size="10.6px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                2) <fo:inline font-weight="bold" margin-left="50px"> | </fo:inline>
                            </fo:block>
                            <fo:block margin-top="20px" text-align="left" font-family="Arial" font-size="10.6px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                <fo:inline font-weight="bold" margin-left="50px"> (назив страног држављанства) </fo:inline>
                                <fo:inline font-weight="bold" margin-left="50px"> (бр. пасоша или ЕБС за стране држављане) </fo:inline>
                            </fo:block>
                        </xsl:when>

                        <xsl:when test="//saglasnost:drzavljanstvo/@vrstaDrzavljanstva = 'strano'">
                            <fo:block margin-top="20px" text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                Држављанство:  <fo:inline font-weight="normal">1) Република Србија | ЈМБГ : </fo:inline>
                            </fo:block>
                            <fo:block margin-left="88px" margin-top="20px" text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                2)
                                <fo:inline-container inline-progression-dimension="40%">
                                    <fo:block margin-left="-20px">
                                        <xsl:value-of select="//saglasnost:drzavljanstvo/saglasnost:stranoDrzavljanstvo"/>
                                    </fo:block>
                                </fo:inline-container>
                                <fo:inline> | </fo:inline>
                                <xsl:choose>
                                    <xsl:when test="//saglasnost:drzavljanstvo/saglasnost:brojPasosa">
                                        <fo:inline-container inline-progression-dimension="30%" font-weight="bold" > <fo:block margin-left="-20px"><xsl:value-of select="//saglasnost:drzavljanstvo/saglasnost:brojPasosa"/></fo:block> </fo:inline-container>
                                    </xsl:when>
                                    <xsl:when test="//saglasnost:drzavljanstvo/saglasnost:EBS">
                                        <fo:inline-container inline-progression-dimension="30%" font-weight="bold" margin-left="50px"> <fo:block margin-left="-20px"><xsl:value-of select="//saglasnost:drzavljanstvo/saglasnost:EBS"/></fo:block> </fo:inline-container>
                                    </xsl:when>
                                </xsl:choose>
                            </fo:block>
                            <fo:block text-align="left" font-family="Arial" font-size="8.6px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                <fo:inline-container inline-progression-dimension="65%">
                                    <fo:block margin-left="115px">
                                        (назив страног држављанства)
                                    </fo:block>
                                </fo:inline-container>
                                <fo:inline-container inline-progression-dimension="40%">
                                    <fo:block margin-left="-25px">
                                        (бр. пасоша или ЕБС за стране држављане)
                                    </fo:block>
                                </fo:inline-container>
                            </fo:block>
                        </xsl:when>
                    </xsl:choose>

                    <fo:block margin-top="10px">
                        <fo:inline-container inline-progression-dimension="33%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                Презиме: <xsl:value-of select="//saglasnost:podaciPacijenta/zaj:prezime"/>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="33%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                | Име: <xsl:value-of select="//saglasnost:podaciPacijenta/zaj:ime"/>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="33%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                | Име родитеља: <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:imeRoditelja"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block margin-top="10px">
                        <fo:inline-container inline-progression-dimension="16%">
                            <xsl:choose>
                                <xsl:when test="//saglasnost:podaciPacijenta/zaj:pol = 'Женско'">
                                    <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                        Пол: <fo:inline font-weight="normal">М, </fo:inline> Ж
                                    </fo:block>
                                </xsl:when>
                                <xsl:otherwise>
                                    <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                        Пол: М <fo:inline font-weight="normal">, Ж</fo:inline>
                                    </fo:block>
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="39%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                | Датум рођења: <xsl:value-of select="format-date(//saglasnost:podaciPacijenta/zaj:datumRodjenja, '[D01].[M01].[Y0001].')"/>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="45%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                | Место рођења: <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:podaciMjesta/saglasnost:mjestoRodjenja"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block margin-top="10px">
                        <fo:inline-container inline-progression-dimension="55%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                Адреса (улица и број): <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:podaciMjesta/saglasnost:adresa/zaj:ulica"/>&#160;
                                <fo:inline> <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:podaciMjesta/saglasnost:adresa/zaj:broj"/> </fo:inline>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                | Место/Насеље: <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:podaciMjesta/saglasnost:mjestoNaselje"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block margin-top="10px">
                        <fo:inline-container inline-progression-dimension="55%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                Општина/Град: <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:podaciMjesta/saglasnost:opstinaGrad"/>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                | Тел. фиксни: <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:kontaktPodaci/zaj:fiksniTelefon"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block margin-top="10px">
                        <fo:inline-container inline-progression-dimension="55%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                Тел. мобилни: <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:kontaktPodaci/zaj:mobilniTelefon"/>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                | имејл: <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:kontaktPodaci/zaj:email"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block margin-top="10px">
                        <xsl:choose>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'запослен'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Радни статус:</fo:inline> <fo:inline font-weight="bold">запослен</fo:inline>, незапослен, пензионер, ученик, студент, дете
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'незапослен'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Радни статус:</fo:inline> запослен, <fo:inline font-weight="bold">незапослен</fo:inline>, пензионер, ученик, студент, дете
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'пензионер'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Радни статус:</fo:inline> запослен, незапослен, <fo:inline font-weight="bold">пензионер</fo:inline>, ученик, студент, дете
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'ученик'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Радни статус:</fo:inline> запослен, незапослен, пензионер, <fo:inline font-weight="bold">ученик</fo:inline>, студент, дете
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'студент'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Радни статус:</fo:inline> запослен, незапослен, пензионер, ученик, <fo:inline font-weight="bold">студент</fo:inline>, дете
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'дете'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Радни статус:</fo:inline> запослен, незапослен, пензионер, ученик, студент, <fo:inline font-weight="bold">дете</fo:inline>
                                </fo:block>
                            </xsl:when>
                        </xsl:choose>
                    </fo:block>

                    <fo:block margin-top="10px">
                        <xsl:choose>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'здравствена заштита'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Занимање запосленог:</fo:inline> <fo:inline font-weight="bold">здравствена заштита</fo:inline>, социјална заштита, просвета, МУП, Војска РС, друго
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'социјална заштита'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Занимање запосленог:</fo:inline> здравствена заштита, <fo:inline font-weight="bold">социјална заштита</fo:inline>, просвета, МУП, Војска РС, друго
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'просвета'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Занимање запосленог:</fo:inline> здравствена заштита, социјална заштита, <fo:inline font-weight="bold">просвета</fo:inline>, МУП, Војска РС, друго
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'МУП'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Занимање запосленог:</fo:inline> здравствена заштита, социјална заштита, просвета, <fo:inline font-weight="bold">МУП</fo:inline>, Војска РС, друго
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'Војска РС'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Занимање запосленог:</fo:inline> здравствена заштита, социјална заштита, просвета, МУП, <fo:inline font-weight="bold">Војска РС</fo:inline>, друго
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'друго'">
                                <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    <fo:inline font-weight="bold">Занимање запосленог:</fo:inline> здравствена заштита, социјална заштита, просвета, МУП, Војска РС, <fo:inline font-weight="bold">друго</fo:inline>
                                </fo:block>
                            </xsl:when>
                        </xsl:choose>
                    </fo:block>

                    <fo:block margin-top="10px">
                        <fo:inline-container inline-progression-dimension="48%">
                            <xsl:choose>
                                <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:socijalnaZastita/@koristiSocijalno = 'ДА'">
                                    <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                        Корисник установе соц. зашт. : ДА <fo:inline font-weight="normal">, НЕ</fo:inline>
                                    </fo:block>
                                </xsl:when>
                                <xsl:otherwise>
                                    <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                        Корисник установе соц. зашт. : <fo:inline font-weight="normal">ДА, </fo:inline> НЕ
                                    </fo:block>
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="60%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                | Назив и општина седишта: <fo:inline><xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:socijalnaZastita/saglasnost:nazivUstanove"/></fo:inline>
                                <fo:inline><xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:socijalnaZastita/saglasnost:sjedisteUstanove"/></fo:inline>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block margin-top="10px">
                        <xsl:choose>
                            <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:izjavaSaglasnosti/@saglasan = 'ДА'">
                                <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    Изјављујем да: CAГЛACAH САМ<fo:inline font-weight="normal">, HИСАМ САГЛАСАН (означити) са спровођењем активне/пасивне имунизације (уписати назив имунолошког лека):</fo:inline>&#160;
                                    <fo:inline> <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:izjavaSaglasnosti/saglasnost:imeLijeka"/> </fo:inline>
                                </fo:block>
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                    Изјављујем да: <fo:inline font-weight="normal">CAГЛACAH САМ </fo:inline> , HИСАМ САГЛАСАН <fo:inline font-weight="normal"> (означити) са спровођењем активне/пасивне имунизације (уписати назив имунолошког лека):</fo:inline>
                                </fo:block>
                            </xsl:otherwise>
                        </xsl:choose>
                    </fo:block>

                    <fo:block margin-top="10px" text-align="left" font-weight="bold" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                        Лекар ми је објаснио предности и ризике од спровођења активне/пасивне имунизације наведеним имунолошким леком.
                    </fo:block>

                    <fo:block margin-left="350px" margin-top="10px" text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                        Датум: <fo:inline font-weight="bold"> <xsl:value-of select="format-date(//saglasnost:podaciPacijenta/saglasnost:datumSaglasnosti, '[D01].[M01].[Y0001].')"/> </fo:inline>
                    </fo:block>

                    <fo:block margin-top="10px" text-align="left" font-weight="bold" font-family="Arial" font-size="6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                        ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
                    </fo:block>

                    <fo:block margin-top="5px" text-align="center" font-weight="bold" font-family="Arial" font-size="16.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        ЕВИДЕНЦИЈА О ВАКЦИНАЦИЈИ ПРОТИВ COVID-19
                    </fo:block >
                    <fo:block text-align="center" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                        (попуњава здравствени радник)
                    </fo:block >

                    <fo:block margin-top="10px">
                        <fo:inline-container inline-progression-dimension="60%">
                            <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                Здравствена установа: <fo:inline font-weight="bold"> <xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:zdravstvenaUstanova/saglasnost:nazivZdravstveneUstanove"/></fo:inline>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="45%">
                            <fo:block text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                                Вакцинацијски пункт: <fo:inline font-weight="bold"> <xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:zdravstvenaUstanova/saglasnost:vakcinacijskiPunkt"/> </fo:inline>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block margin-top="10px" text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                        Име, презиме, факсимил и бр. телефона лекара: <fo:inline font-weight="bold"><xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:podaciLjekara/saglasnost:ime"/></fo:inline>&#160;
                        <fo:inline font-weight="bold"><xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:podaciLjekara/saglasnost:prezime"/></fo:inline>, (тел)
                        <fo:inline font-weight="bold"><xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:podaciLjekara/saglasnost:brojTelefona"/></fo:inline>, (факс)
                        <fo:inline font-weight="bold"><xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:podaciLjekara/saglasnost:faksimil"/></fo:inline>
                    </fo:block>

                    <fo:block margin-top="10px" text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                        Пре давања вакцине прегледати особу и упознати је са користима и о могућим нежељеним реакцијама после вакцинације. Обавезно уписати сваку дату вакцину и све тражене податке у овај образац и податке унети у лични картон о извршеним имунизацијама и здравствени картон.
                    </fo:block>

                    <fo:table font-family="Arial" border="1px" border-collapse="collapse" margin-top="10px">
                        <fo:table-column/>
                        <fo:table-column column-width="15%"/>
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-column column-width="7%"/>
                        <fo:table-body>
                            <fo:table-row border="1px solid black">
                                <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    <fo:block text-align="center">Назив вакцине</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    <fo:block text-align="center">Датум давања вакцине (V1 i V2)</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    <fo:block text-align="center">Начин давања вакцине</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    <fo:block text-align="center">Екстремитет</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    <fo:block text-align="center">Серија вакцине (лот)</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    <fo:block text-align="center">Произвођач</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    <fo:block text-align="center">Нежељена реакција</fo:block>
                                </fo:table-cell>
                                <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    <fo:block text-align="center">Лекар</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <xsl:for-each select="//saglasnost:podaciVakcinacija/saglasnost:vakcinacija">
                                <fo:table-row border="1px solid black">
                                    <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                        <fo:block text-align="center"><xsl:value-of select="saglasnost:nazivVakcine"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                        <fo:block text-align="center"><xsl:value-of select="format-date(saglasnost:datumDavanjaVakcine, '[D01].[M01].[Y0001].')"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                        <fo:block text-align="center"><xsl:value-of select="saglasnost:nacinDavanjaVakcine"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                        <fo:block text-align="center"><xsl:value-of select="saglasnost:ekstremitet"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                        <fo:block text-align="center"><xsl:value-of select="saglasnost:serijaVakcine"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                        <fo:block text-align="center"><xsl:value-of select="saglasnost:proizvodjac"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                        <fo:block text-align="center"><xsl:value-of select="saglasnost:nezeljenaReakcija"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                            <xsl:for-each select="//saglasnost:podaciVakcinacija/saglasnost:privremeneKontraindikacije/saglasnost:kontraindikacija">
                                <fo:table-row border="1px solid black">
                                    <fo:table-cell number-columns-spanned="8" border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                        <fo:block text-align="left">Привремене контраиндикације (датум утврђивања и дијагноза):
                                            <fo:inline><xsl:value-of select="format-date(saglasnost:datumUtvrdjivanja, '[D01].[M01].[Y0001].')"/></fo:inline> -
                                            <fo:inline> <xsl:value-of select="saglasnost:dijagnoza"/> </fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                            <fo:table-row border="1px solid black">
                                <fo:table-cell number-columns-spanned="8" border-right="1px solid black" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    <fo:block text-align="left">Одлука комисије за трајне контраиндикације (ако постоји, уписати Да):
                                        <fo:inline><xsl:value-of select="//saglasnost:podaciVakcinacija/saglasnost:odlukaKomisije"/></fo:inline>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block margin-top="40px" text-align="left" font-weight="normal" font-family="Arial" font-size="10.6px" color="rgb(43,42,41)" font-style="normal" text-decoration="none">
                        <fo:inline font-weight="bold"> Напомена: </fo:inline> Образац се чува као део медицинске документације пацијента.
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>