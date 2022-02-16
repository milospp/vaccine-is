<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:inter="http://www.ftn.uns.ac.rs/interesovanje"
                xmlns:zaj="http://www.ftn.uns.ac.rs/zajednicka"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="interesovanje-page" page-width="595px" page-height="841px">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="interesovanje-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block margin-top="10px" text-align="center" font-weight="bold" font-family="Times New Roman" font-size="13.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Исказивање интересовања за вакцинисање против COVID-19
                    </fo:block >
                    <fo:block margin-top="10px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Одаберите опцију:
                    </fo:block>
                    <fo:block-container margin-left="20px">
                        <xsl:choose>
                            <xsl:when test="//inter:drzavljanstvo = 'Држављанин Републике Србије'">
                                <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Држављанин Републике Србије
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Страни држављанин са боравком у РС
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Страни држављанин без боравка у РС
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//inter:drzavljanstvo = 'Страни држављанин са боравком у РС'">
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Држављанин Републике Србије
                                </fo:block>
                                <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Страни држављанин са боравком у РС
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Страни држављанин без боравка у РС
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//inter:drzavljanstvo = 'Страни држављанин без боравка у РС'">
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Држављанин Републике Србије
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Страни држављанин са боравком у РС
                                </fo:block>
                                <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Страни држављанин без боравка у РС
                                </fo:block>
                            </xsl:when>
                        </xsl:choose>
                    </fo:block-container>

                    <fo:block margin-top="10px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        ЈМБГ:
                    </fo:block>
                    <fo:block margin-left="40px" margin-top="5px" font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        <xsl:value-of select="//inter:jmbg"/>
                    </fo:block>

                    <fo:block margin-top="10px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Име:
                    </fo:block>
                    <fo:block margin-left="40px" margin-top="5px" font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        <xsl:value-of select="//inter:ime"/>
                    </fo:block>

                    <fo:block margin-top="10px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Презиме:
                    </fo:block>
                    <fo:block margin-left="40px" margin-top="5px" font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        <xsl:value-of select="//inter:prezime"/>
                    </fo:block>

                    <fo:block margin-top="10px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Адреса електронске поште:
                    </fo:block>
                    <fo:block margin-left="40px" margin-top="5px" font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        <xsl:value-of select="//inter:kontakt/zaj:email"/>
                    </fo:block>

                    <fo:block margin-top="10px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Број мобилног телефона (навести број у формату 06X..... без размака и цртица):
                    </fo:block>
                    <fo:block margin-left="40px" margin-top="5px" font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        <xsl:value-of select="//inter:kontakt/zaj:mobilniTelefon"/>
                    </fo:block>

                    <fo:block margin-top="10px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Број фиксног телефона (навести број у формату нпр. 011..... без размака и цртица):
                    </fo:block>
                    <fo:block margin-left="40px" margin-top="5px" font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        <xsl:value-of select="//inter:kontakt/zaj:fiksniTelefon"/>
                    </fo:block>

                    <fo:block margin-top="10px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Одаберите локацију где желите да примите вакцину (унесите општину):
                    </fo:block>
                    <fo:block margin-left="40px" margin-top="5px" font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        <xsl:value-of select="//inter:lokacijaPrimanja"/>
                    </fo:block>

                    <fo:block margin-top="15px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Исказујем интересовање да примим искључиво вакцину следећих произвођача за који Агенција за лекове и медицинска средства потврди безбедност, ефикасност и квалитет и изда дозволу за употребу лека:
                    </fo:block>
                    <fo:block-container margin-left="20px">
                        <xsl:choose>
                            <xsl:when test="//inter:nazivVakcine = 'Pfizer-BioNTech'">
                                <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Pfizer-BioNTech
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sputnik V (Gamaleya истраживачки центар)
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sinopharm
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    AstraZeneca
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Moderna
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Било која
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//inter:nazivVakcine = 'Sputnik V (Gamaleya истраживачки центар)'">
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Pfizer-BioNTech
                                </fo:block>
                                <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sputnik V (Gamaleya истраживачки центар)
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sinopharm
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    AstraZeneca
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Moderna
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Било која
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//inter:nazivVakcine = 'Sinopharm'">
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Pfizer-BioNTech
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sputnik V (Gamaleya истраживачки центар)
                                </fo:block>
                                <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sinopharm
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    AstraZeneca
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Moderna
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Било која
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//inter:nazivVakcine = 'AstraZeneca'">
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Pfizer-BioNTech
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sputnik V (Gamaleya истраживачки центар)
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sinopharm
                                </fo:block>
                                <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    AstraZeneca
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Moderna
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Било која
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//inter:nazivVakcine = 'Moderna'">
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Pfizer-BioNTech
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sputnik V (Gamaleya истраживачки центар)
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sinopharm
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    AstraZeneca
                                </fo:block>
                                <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Moderna
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Било која
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//inter:nazivVakcine = 'Било која'">
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Pfizer-BioNTech
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sputnik V (Gamaleya истраживачки центар)
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Sinopharm
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    AstraZeneca
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Moderna
                                </fo:block>
                                <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Било која
                                </fo:block>
                            </xsl:when>
                        </xsl:choose>
                    </fo:block-container>

                    <fo:block margin-top="15px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Да ли сте добровољни давалац крви?
                    </fo:block>
                    <fo:block-container margin-left="20px">
                        <xsl:choose>
                            <xsl:when test="//inter:davalacKrvi = 'Да'">
                                <fo:block font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Да
                                </fo:block>
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Не
                                </fo:block>
                            </xsl:when>
                            <xsl:when test="//inter:davalacKrvi = 'Не'">
                                <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Да
                                </fo:block>
                                <fo:block font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Не
                                </fo:block>
                            </xsl:when>
                        </xsl:choose>
                    </fo:block-container>

                    <fo:block margin-top="55px" font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        дана <xsl:value-of select="format-date(//inter:datum, '[D01].[M01].[Y0001]')"/> године
                    </fo:block>

                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>