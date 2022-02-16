<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:izvjestaj="http://www.ftn.uns.ac.rs/izvjestaj"
                xmlns:zaj="http://www.ftn.uns.ac.rs/zajednicka"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="izvjestaj-page" page-width="595px" page-height="841px">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="izvjestaj-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block margin-top="10px" text-align="center" font-weight="bold" font-family="Times New Roman" font-size="13.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Извештај о имунизацији
                    </fo:block >

                    <fo:block margin-top="40px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Извештај се односи на период од <fo:inline font-weight="bold"><xsl:value-of select="format-date(//izvjestaj:period/izvjestaj:pocetakPerioda, '[D01].[M01].[Y0001]')"/></fo:inline>
                        до <fo:inline font-weight="bold"><xsl:value-of select="format-date(//izvjestaj:period/izvjestaj:krajPerioda, '[D01].[M01].[Y0001]')"/></fo:inline>
                    </fo:block>

                    <fo:block margin-top="30px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        У напоменутом временском интервалу је:
                    </fo:block >
                    <fo:block-container>
                        <fo:block start-indent="20pt" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                            &#9679; поднето <fo:inline font-weight="bold"><xsl:value-of select="//izvjestaj:brojInteresovanjaImunizacija"/></fo:inline> докумената  о интересовању за имунизацију;
                        </fo:block>
                        <fo:block start-indent="20pt" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                            &#9679; примљено <fo:inline font-weight="bold"><xsl:value-of select="//izvjestaj:brojZahtjeva"/></fo:inline> захтева за дигитални зелени сертификат, од којих је
                            <fo:inline font-weight="bold"><xsl:value-of select="//izvjestaj:izdatoZahtjeva"/></fo:inline> издато.
                        </fo:block>
                    </fo:block-container>

                    <fo:block margin-top="40px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Дато је <fo:inline font-weight="bold"><xsl:value-of select="//izvjestaj:brojDoza"/></fo:inline> доза вакцине против COVID-19 вируса у следећој количини:
                    </fo:block >

                    <fo:block margin-top="10px">
                        <fo:table font-family="Times New Roman" border="1px" border-collapse="collapse">
                            <fo:table-column column-width="50%"/>
                            <fo:table-column column-width="50%"/>
                            <fo:table-body>
                                <fo:table-row border="1px solid black">
                                    <fo:table-cell border-right="1px solid black" font-family="Times New Roman" font-weight="bold" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                        <fo:block text-align="center">Редни број дозе</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-family="Times New Roman" font-weight="bold" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                        <fo:block text-align="center">Број датих доза</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <xsl:for-each select="//izvjestaj:dozeVakcinaInfo/izvjestaj:dozaInfo">
                                    <xsl:if test="izvjestaj:redniBrojDoze = '1'">
                                        <fo:table-row border="1px solid black">
                                            <fo:table-cell border-right="1px solid black" font-family="Times New Roman" font-weight="bold" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="center">1</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="center"><xsl:value-of select="izvjestaj:brojDatihDoza"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:if>
                                    <xsl:if test="izvjestaj:redniBrojDoze = '2'">
                                        <fo:table-row border="1px solid black">
                                            <fo:table-cell border-right="1px solid black" font-family="Times New Roman" font-weight="bold" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="center">2</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="center"><xsl:value-of select="izvjestaj:brojDatihDoza"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:if>
                                    <xsl:if test="izvjestaj:redniBrojDoze = '3'">
                                        <fo:table-row border="1px solid black">
                                            <fo:table-cell border-right="1px solid black" font-family="Times New Roman" font-weight="bold" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="center">3</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="center"><xsl:value-of select="izvjestaj:brojDatihDoza"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:if>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

                    <fo:block margin-top="40px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Расподела по произвођачима је:
                    </fo:block >
                    <fo:block-container>
                        <xsl:for-each select="//izvjestaj:raspodjelaDoza/izvjestaj:tipVakcineInfo">
                            <xsl:if test="izvjestaj:nazivProizvodjaca = 'Pfizer, BioNTech'">
                                <fo:block start-indent="20pt" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    &#9679; <fo:inline font-weight="bold">Pfizer, BioNTech -</fo:inline> <fo:inline font-weight="bold"><xsl:value-of select="izvjestaj:brojDozaProizvodjac"/></fo:inline> доза;
                                </fo:block>
                            </xsl:if>

                            <xsl:if test="izvjestaj:nazivProizvodjaca = 'Sinopharm'">
                                <fo:block start-indent="20pt" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    &#9679; <fo:inline font-weight="bold">Sinopharm -</fo:inline> <fo:inline font-weight="bold"><xsl:value-of select="izvjestaj:brojDozaProizvodjac"/></fo:inline> доза;
                                </fo:block>
                            </xsl:if>

                            <xsl:if test="izvjestaj:nazivProizvodjaca = 'Sputnik V'">
                                <fo:block start-indent="20pt" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    &#9679; <fo:inline font-weight="bold">Sputnik V -</fo:inline> <fo:inline font-weight="bold"><xsl:value-of select="izvjestaj:brojDozaProizvodjac"/></fo:inline> доза;
                                </fo:block>
                            </xsl:if>

                            <xsl:if test="izvjestaj:nazivProizvodjaca = 'AstraZeneca, Oxford'">
                                <fo:block start-indent="20pt" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    &#9679; <fo:inline font-weight="bold">AstraZeneca, Oxford -</fo:inline> <fo:inline font-weight="bold"><xsl:value-of select="izvjestaj:brojDozaProizvodjac"/></fo:inline> доза;
                                </fo:block>
                            </xsl:if>

                            <xsl:if test="izvjestaj:nazivProizvodjaca = 'Moderna'">
                                <fo:block start-indent="20pt" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    &#9679; <fo:inline font-weight="bold">Moderna -</fo:inline> <fo:inline font-weight="bold"><xsl:value-of select="izvjestaj:brojDozaProizvodjac"/></fo:inline> доза;
                                </fo:block>
                            </xsl:if>
                        </xsl:for-each>
                    </fo:block-container>

                    <fo:block margin-top="70px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Датум издавања: <fo:inline font-weight="bold" text-decoration="underline"><xsl:value-of select="format-date(//izvjestaj:datumIzdavanjaIzvjestaja, '[D01].[M01].[Y0001]')"/></fo:inline> године
                    </fo:block >
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>