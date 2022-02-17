<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:saglasnost="http://www.ftn.uns.ac.rs/obrazac-saglasnosti"
                xmlns:zaj="http://www.ftn.uns.ac.rs/zajednicka"
                xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>САГЛАСНОСТ ЗА СПРОВОЂЕЊЕ ПРЕПОРУЧЕНЕ ИМУНИЗАЦИЈЕ</title>
                <style type="text/css">
                    span.cls_002{font-family:Arial,serif;font-size:17px;color:rgb(43,42,41);font-weight:bold;font-style:normal;text-decoration: none}
                    div.cls_002{font-family:Arial,serif;font-size:17px;color:rgb(43,42,41);font-weight:bold;font-style:normal;text-decoration: none}
                    span.cls_003{font-family:Arial,serif;font-size:8.6px;color:rgb(43,42,41);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_003{font-family:Arial,serif;font-size:8.6px;color:rgb(43,42,41);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_006{font-family:Arial,serif;font-size:14.6px;color:rgb(43,42,41);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_006{font-family:Arial,serif;font-size:14.6px;color:rgb(43,42,41);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_007{font-family:Arial,serif;font-size:10.6px;color:rgb(43,42,41);font-weight:bold;font-style:normal;text-decoration: none}
                    div.cls_007{font-family:Arial,serif;font-size:10.6px;color:rgb(43,42,41);font-weight:bold;font-style:normal;text-decoration: none}
                    span.cls_008{font-family:Arial,serif;font-size:10.6px;color:rgb(43,42,41);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_008{font-family:Arial,serif;font-size:10.6px;color:rgb(43,42,41);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_009{font-family:Arial,serif;font-size:16.1px;color:rgb(43,42,41);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_009{font-family:Arial,serif;font-size:16.1px;color:rgb(43,42,41);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_010{font-family:Arial,serif;font-size:9.1px;color:rgb(43,42,41);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_010{font-family:Arial,serif;font-size:9.1px;color:rgb(43,42,41);font-weight:normal;font-style:normal;text-decoration: none}
                    table, th, td { border: 1px solid black; border-collapse: collapse; text-align: center; }
                </style>
            </head>
            <body>

                <div style="position:absolute;left:50%;margin-left:-297px;top:0px;width:595px;height:841px;border-style:outset;overflow:hidden">
                    <div style="position:absolute;left:0px;top:0px">
                        <img width="595" height="841"/>
                    </div>
                    <img src="../../../static/images/Screenshot from 2022-02-08 11-18-10.png" style="position: absolute; left: 370px; top: 36px; height: 70px;"/>
                    <div style="position:absolute;left:42.52px;top:36.63px" class="cls_002"><span class="cls_002">САГЛАСНОСТ ЗА СПРОВОЂЕЊЕ</span></div>
                    <div style="position:absolute;left:445.91px;top:48.09px" class="cls_003"><span class="cls_003">ИНСТИТУТ ЗА</span></div>
                    <div style="position:absolute;left:445.91px;top:58.29px" class="cls_003"><span class="cls_003">ЈАВНО ЗДРАВЉЕ СРБИЈЕ</span></div>
                    <div style="position:absolute;left:445.91px;top:68.49px" class="cls_003"><span class="cls_003">„Др Милан Јовановић Батут“</span></div>
                    <div style="position:absolute;left:42.52px;top:60.63px" class="cls_002"><span class="cls_002">ПРЕПОРУЧЕНЕ ИМУНИЗАЦИЈЕ</span></div>
                    <div style="position:absolute;left:42.52px;top:83.53px" class="cls_006"><span class="cls_006">(попуњава пацијент)</span></div>
                    <div style="position:absolute;left:42.52px;top:126.41px" class="cls_007"><span class="cls_007">Држављанство</span></div>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:drzavljanstvo/@vrstaDrzavljanstva = 'srpsko'">
                            <div style="position:absolute;left:130px;top:126.41px" class="cls_008"><span class="cls_008"><b>1) Република Србија | </b></span></div>
                            <div style="position:absolute;left:250px;top:126.41px" class="cls_008"><span class="cls_008">ЈМБГ :
                                <b><xsl:value-of select="//saglasnost:drzavljanstvo/saglasnost:jmbg"/></b></span></div>

                            <div style="position:absolute;left:130px;top:152.70px" class="cls_008"><span class="cls_008">2)</span></div>
                            <div style="position:absolute;left:300px;top:152.70px" class="cls_008"><span class="cls_008"> | </span></div>
                        </xsl:when>

                        <xsl:when test="//saglasnost:drzavljanstvo/@vrstaDrzavljanstva = 'strano'">
                            <div style="position:absolute;left:130px;top:126.41px" class="cls_008"><span class="cls_008">1) Република Србија | </span></div>
                            <div style="position:absolute;left:240px;top:126.41px" class="cls_008"><span class="cls_008">ЈМБГ : </span></div>
                            <div style="position:absolute;left:130px;top:152.70px" class="cls_008"><span class="cls_008"><b>2)
                                <span style="margin-left:60px;"><xsl:value-of select="//saglasnost:drzavljanstvo/saglasnost:stranoDrzavljanstvo"/></span></b></span></div>
                            <div style="position:absolute;left:300px;top:152.70px" class="cls_008"><span class="cls_008"> |
                                <xsl:choose>
                                    <xsl:when test="//saglasnost:drzavljanstvo/saglasnost:brojPasosa">
                                        <b><span style="margin-left:80px;"><xsl:value-of select="//saglasnost:drzavljanstvo/saglasnost:brojPasosa"/></span></b>
                                    </xsl:when>
                                    <xsl:when test="//saglasnost:drzavljanstvo/saglasnost:EBS">
                                        <b><span style="margin-left:80px;"><xsl:value-of select="//saglasnost:drzavljanstvo/saglasnost:EBS"/></span></b>
                                    </xsl:when>
                                </xsl:choose>
                            </span></div>
                        </xsl:when>
                    </xsl:choose>

                    <div style="position:absolute;left:150px;top:167.30px" class="cls_003"><span class="cls_003">(назив страног држављанства)</span></div>
                    <div style="position:absolute;left:330px;top:167.30px" class="cls_003"><span class="cls_003">(бр. пасоша или ЕБС за стране држављане)</span></div>

                    <div style="position:absolute;left:42.52px;top:191.58px" class="cls_007"><span class="cls_007">Презиме:
                        <xsl:value-of select="//saglasnost:podaciPacijenta/zaj:prezime"/></span></div>

                    <div style="position:absolute;left:200px;top:191.58px" class="cls_007"><span class="cls_007">| Име:
                        <xsl:value-of select="//saglasnost:podaciPacijenta/zaj:ime"/></span></div>

                    <div style="position:absolute;left:360px;top:191.58px" class="cls_007"><span class="cls_007">| Име родитеља:
                        <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:imeRoditelja"/></span></div>

                    <div style="position:absolute;left:42.52px;top:217.86px" class="cls_007"><span class="cls_007">Пол</span><span class="cls_008">:</span></div>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/zaj:pol = 'Женско'">
                            <div style="position:absolute;left:76.70px;top:217.86px" class="cls_008"><span class="cls_008">М,</span></div>
                            <div style="position:absolute;left:101.72px;top:217.86px" class="cls_008"><span class="cls_008"><b>Ж</b></span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:76.70px;top:217.86px" class="cls_008"><span class="cls_008"><b>М</b>,</span></div>
                            <div style="position:absolute;left:101.72px;top:217.86px" class="cls_008"><span class="cls_008">Ж </span></div>
                        </xsl:otherwise>
                    </xsl:choose>

                    <div style="position:absolute;left:118.47px;top:217.86px" class="cls_007"><span class="cls_007"> | Датум рођења:
                        <xsl:value-of select="format-date(//saglasnost:podaciPacijenta/zaj:datumRodjenja, '[D01].[M01].[Y0001].')"/></span></div>

                    <div style="position:absolute;left:303.47px;top:217.86px" class="cls_007"><span class="cls_007"> | Место рођења:
                        <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:podaciMjesta/saglasnost:mjestoRodjenja"/></span></div>

                    <div style="position:absolute;left:42.52px;top:244.14px" class="cls_007"><span class="cls_007">Адреса (улица и број):
                        <span style="margin-right:3px;"><xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:podaciMjesta/saglasnost:adresa/zaj:ulica"/></span>
                        <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:podaciMjesta/saglasnost:adresa/zaj:broj"/></span></div>

                    <div style="position:absolute;left:339.47px;top:244.14px" class="cls_007"><span class="cls_007"> | Место/Насеље:
                        <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:podaciMjesta/saglasnost:mjestoNaselje"/></span></div>

                    <div style="position:absolute;left:42.52px;top:270.43px" class="cls_007"><span class="cls_007">Општина/Град:
                        <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:podaciMjesta/saglasnost:opstinaGrad"/></span></div>

                    <div style="position:absolute;left:339.47px;top:270.43px" class="cls_007"><span class="cls_007">| Тел. фиксни:
                        <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:kontaktPodaci/zaj:fiksniTelefon"/></span></div>

                    <div style="position:absolute;left:42.52px;top:296.71px" class="cls_007"><span class="cls_007">Тел. мобилни:
                        <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:kontaktPodaci/zaj:mobilniTelefon"/></span></div>

                    <div style="position:absolute;left:303.49px;top:296.71px" class="cls_007"><span class="cls_007"> | имејл:
                        <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:kontaktPodaci/zaj:email"/></span></div>

                    <div style="position:absolute;left:42.52px;top:321.91px" class="cls_007"><span class="cls_007">Радни статус</span><span class="cls_008">:</span></div>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'запослен'">
                            <div style="position:absolute;left:118.86px;top:321.91px" class="cls_008"><span class="cls_008"><b>запослен</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:118.86px;top:321.91px" class="cls_008"><span class="cls_008">запослен,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'незапослен'">
                            <div style="position:absolute;left:176.87px;top:321.91px" class="cls_008"><span class="cls_008"><b>незапослен</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:176.87px;top:321.91px" class="cls_008"><span class="cls_008">незапослен,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'пензионер'">
                            <div style="position:absolute;left:244.82px;top:321.91px" class="cls_008"><span class="cls_008"><b>пензионер</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:244.82px;top:321.91px" class="cls_008"><span class="cls_008">пензионер,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'ученик'">
                            <div style="position:absolute;left:308.31px;top:321.91px" class="cls_008"><span class="cls_008"><b>ученик</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:308.31px;top:321.91px" class="cls_008"><span class="cls_008">ученик,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'студент'">
                            <div style="position:absolute;left:356.13px;top:321.91px" class="cls_008"><span class="cls_008"><b>студент</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:356.13px;top:321.91px" class="cls_008"><span class="cls_008">студент,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:radniStatus = 'дете'">
                            <div style="position:absolute;left:408.48px;top:321.91px" class="cls_008"><span class="cls_008"><b>дете</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:408.48px;top:321.91px" class="cls_008"><span class="cls_008">дете,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    
                    <div style="position:absolute;left:42.52px;top:347.11px" class="cls_007"><span class="cls_007">Занимање запосленог</span><span class="cls_008">:</span></div>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'здравствена заштита'">
                            <div style="position:absolute;left:165px;top:347.11px" class="cls_008"><span class="cls_008"><b>здравствена заштита</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:165px;top:347.11px" class="cls_008"><span class="cls_008">здравствена заштита,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'социјална заштита'">
                            <div style="position:absolute;left:275px;top:347.11px" class="cls_008"><span class="cls_008"><b>социјална заштита</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:275px;top:347.11px" class="cls_008"><span class="cls_008">социјална заштита,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'просвета'">
                            <div style="position:absolute;left:373px;top:347.11px" class="cls_008"><span class="cls_008"><b>просвета</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:373px;top:347.11px" class="cls_008"><span class="cls_008">просвета,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'МУП'">
                            <div style="position:absolute;left:424.69px;top:347.11px" class="cls_008"><span class="cls_008"><b>МУП</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:424.69px;top:347.11px" class="cls_008"><span class="cls_008">МУП,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'Војска РС'">
                            <div style="position:absolute;left:455px;top:347.11px" class="cls_008"><span class="cls_008"><b>Војска РС</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:455px;top:347.11px" class="cls_008"><span class="cls_008">Војска РС,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:zanimanjeZaposlenog = 'друго'">
                            <div style="position:absolute;left:510px;top:347.11px" class="cls_008"><span class="cls_008"><b>друго</b>,</span></div>
                        </xsl:when>
                        <xsl:otherwise>
                            <div style="position:absolute;left:510px;top:347.11px" class="cls_008"><span class="cls_008">друго,</span></div>
                        </xsl:otherwise>
                    </xsl:choose>
                    
                    <div style="position:absolute;left:42.52px;top:373.39px" class="cls_007"><span class="cls_007">Корисник установе соц. зашт.</span></div>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:socijalnaZastita/@koristiSocijalno = 'ДА'">
                            <div style="position:absolute;left:208px;top:373.39px" class="cls_008"><span class="cls_008"><b>ДА</b>, </span></div>
                            <div style="position:absolute;left:230px;top:373.39px" class="cls_008"><span class="cls_008">НЕ</span></div>
                            <div style="position:absolute;left:255px;top:373.39px" class="cls_007"><span class="cls_007"> | Назив и општина седишта:
                                <span style="margin-right:3px;"><xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:socijalnaZastita/saglasnost:nazivUstanove"/></span>
                                <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:socijalnaZastita/saglasnost:sjedisteUstanove"/></span></div>
                        </xsl:when>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:socijalnaZastita/@koristiSocijalno = 'НЕ'">
                            <div style="position:absolute;left:208px;top:373.39px" class="cls_008"><span class="cls_008">ДА, </span></div>
                            <div style="position:absolute;left:230px;top:373.39px" class="cls_008"><span class="cls_008"><b>НЕ</b></span></div>
                        </xsl:when>
                    </xsl:choose>

                    <!-- OVDJE -->
                    <div style="position:absolute;left:42.52px;top:398.59px" class="cls_007"><span class="cls_007">Изјављујем да</span><span class="cls_008">:</span></div>
                    <xsl:choose>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:izjavaSaglasnosti/@saglasan = 'ДА'">
                            <div style="position:absolute;left:124.98px;top:398.59px" class="cls_007"><span class="cls_007">CAГЛACAH САМ</span><span class="cls_008">, </span></div>
                            <div style="position:absolute;left:220px;top:398.59px" class="cls_007"><span class="cls_008">HИСАМ CAГЛACAH</span></div>
                            <div style="position:absolute;left:285px;top:411.19px" class="cls_008"><span class="cls_007">
                                <xsl:value-of select="//saglasnost:podaciPacijenta/saglasnost:izjavaSaglasnosti/saglasnost:imeLijeka"/></span></div>
                        </xsl:when>
                        <xsl:when test="//saglasnost:podaciPacijenta/saglasnost:izjavaSaglasnosti/@saglasan = 'НЕ'">
                            <div style="position:absolute;left:124.98px;top:398.59px" class="cls_007"><span class="cls_008">CAГЛACAH САМ, </span></div>
                            <div style="position:absolute;left:220px;top:398.59px" class="cls_007"><span class="cls_007">HИСАМ CAГЛACAH</span></div>
                        </xsl:when>
                    </xsl:choose>

                    <div style="position:absolute;left:325px;top:398.59px" class="cls_007"><span class="cls_008"> (означити) са спровођењем активне/пасивне</span></div>
                    <div style="position:absolute;left:42.52px;top:411.19px" class="cls_008"><span class="cls_008">имунизације (уписати назив имунолошког лека):</span></div>

                    <div style="position:absolute;left:42.52px;top:436.39px" class="cls_007"><span class="cls_007">Лекар ми је објаснио предности и ризике од спровођења активне/пасивне имунизације наведеним имунолошким леком.</span></div>

                    <div style="position:absolute;left:402.52px;top:474.19px" class="cls_008"><span class="cls_008">Датум:
                        <b><xsl:value-of select="format-date(//saglasnost:podaciPacijenta/saglasnost:datumSaglasnosti, '[D01].[M01].[Y0001].')"/></b></span></div>
                    <!-- OVDJE -->
                    <div style="position:absolute;left:42px;top:490px"><span style="font-size: 6px;">|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||</span></div>

                    <div style="position:absolute;left:105.29px;top:505px" class="cls_009"><span class="cls_009">ЕВИДЕНЦИЈА О ВАКЦИНАЦИЈИ ПРОТИВ COVID-19</span></div>
                    <div style="position:absolute;left:226.94px;top:525px" class="cls_008"><span class="cls_008">(попуњава здравствени радник)</span></div>
                    <div style="position:absolute;left:42.52px;top:550px" class="cls_008"><span class="cls_008">Здравствена установа:
                        <b><xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:zdravstvenaUstanova/saglasnost:nazivZdravstveneUstanove"/></b></span></div>
                    <div style="position:absolute;left:337.45px;top:550px" class="cls_008"><span class="cls_008">Вакцинацијски пункт:
                        <b><xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:zdravstvenaUstanova/saglasnost:vakcinacijskiPunkt"/></b></span></div>
                    <div style="position:absolute;left:42.52px;top:575px" class="cls_008"><span class="cls_008">Име, презиме, факсимил и бр. телефона лекара:
                        <span style="margin-right:3px;"><b><xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:podaciLjekara/saglasnost:ime"/></b></span>
                        <b><xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:podaciLjekara/saglasnost:prezime"/></b>, (тел)
                        <b><xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:podaciLjekara/saglasnost:brojTelefona"/></b>, (факс)
                        <b><xsl:value-of select="//saglasnost:evidencijaVakcinacije/saglasnost:podaciLjekara/saglasnost:faksimil"/></b></span></div>
                    <div style="position:absolute;left:42.52px;top:595px" class="cls_008"><span class="cls_008">Пре давања вакцине прегледати особу и упознати је са користима и о могућим нежељеним реакцијама</span></div>
                    <div style="position:absolute;left:42.52px;top:610px" class="cls_008"><span class="cls_008">после вакцинације. Обавезно уписати сваку дату вакцину и све тражене податке у овај образац и податке</span></div>
                    <div style="position:absolute;left:42.52px;top:625px" class="cls_008"><span class="cls_008">унети у лични картон о извршеним имунизацијама и здравствени картон.</span></div>

                    <table style="position:absolute;left:42px;top:650px; min-height: 70px; width: 520px;">
                        <tr>
                            <th>
                                <div class="cls_010"><span class="cls_010">Назив вакцине</span></div>
                            </th>
                            <th>
                                <div class="cls_010"><span class="cls_010">Датум давања вакцине (V1 i V2)</span></div>
                            </th>
                            <th>
                                <div class="cls_010"><span class="cls_010">Начин давања вакцине</span></div>
                            </th>
                            <th>
                                <div class="cls_010"><span class="cls_010">Екстремитет</span></div>
                            </th>
                            <th>
                                <div class="cls_010"><span class="cls_010">Серија вакцине (лот)</span></div>
                            </th>
                            <th>
                                <div class="cls_010"><span class="cls_010">Произвођач</span></div>
                            </th>
                            <th>
                                <div class="cls_010"><span class="cls_010">Нежељена реакција</span></div>
                            </th>
                            <th>
                                <div class="cls_010"><span class="cls_010">Лекар</span></div>
                            </th>
                        </tr>

                        <xsl:for-each select="//saglasnost:podaciVakcinacija/saglasnost:vakcinacija">
                            <tr>
                                <td><div class="cls_010"><span class="cls_010"><b><xsl:value-of select="saglasnost:nazivVakcine"/></b></span></div></td>
                                <td><div class="cls_010"><span class="cls_010"><b><xsl:value-of select="format-date(saglasnost:datumDavanjaVakcine, '[D01].[M01].[Y0001].')"/></b></span></div></td>
                                <td><div class="cls_010"><span class="cls_010"><b><xsl:value-of select="saglasnost:nacinDavanjaVakcine"/></b></span></div></td>
                                <td><div class="cls_010"><span class="cls_010"><b><xsl:value-of select="saglasnost:ekstremitet"/></b></span></div></td>
                                <td><div class="cls_010"><span class="cls_010"><b><xsl:value-of select="saglasnost:serijaVakcine"/></b></span></div></td>
                                <td><div class="cls_010"><span class="cls_010"><b><xsl:value-of select="saglasnost:proizvodjac"/></b></span></div></td>
                                <td><div class="cls_010"><span class="cls_010"><b><xsl:value-of select="saglasnost:nezeljenaReakcija"/></b></span></div></td>
                            </tr>
                        </xsl:for-each>

                        <xsl:for-each select="//saglasnost:podaciVakcinacija/saglasnost:privremeneKontraindikacije/saglasnost:kontraindikacija">
                            <tr>
                                <td colspan="8" style="text-align: left;">
                                    <div class="cls_010"><span class="cls_010">Привремене контраиндикације (датум утврђивања и дијагноза):
                                        датум: <b><xsl:value-of select="format-date(saglasnost:datumUtvrdjivanja, '[D01].[M01].[Y0001].')"/></b>
                                        , дијагноза: <b><xsl:value-of select="saglasnost:dijagnoza"/></b></span></div>
                                </td>
                            </tr>
                        </xsl:for-each>

                        <tr>
                            <td colspan="8" style="text-align: left;">
                                <div class="cls_010"><span class="cls_010">Одлука комисије за трајне контраиндикације (ако постоји, уписати Да):
                                    <b><xsl:value-of select="//saglasnost:podaciVakcinacija/saglasnost:odlukaKomisije"/></b></span></div>
                            </td>
                        </tr>
                    </table>

                    <div style="position:absolute;left:42.52px;top:800px" class="cls_007"><span class="cls_007">Напомена</span><span class="cls_008">: Образац се чува као део медицинске документације пацијента.</span></div>
                </div>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>