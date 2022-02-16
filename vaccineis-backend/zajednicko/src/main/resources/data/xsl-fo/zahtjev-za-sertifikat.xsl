<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:zahtjev="http://www.ftn.uns.ac.rs/zahtjevSertifikata"
                xmlns:zaj="http://www.ftn.uns.ac.rs/zajednicka"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="zahtjev-page" page-width="595px" page-height="841px">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="zahtjev-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block margin-top="50px" text-align="center" font-weight="bold" font-family="Times New Roman" font-size="13.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        З А Х Т Е В
                    </fo:block >
                    <fo:block text-align="center" font-weight="bold" font-family="Times New Roman" font-size="13.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        за издавање дигиталног зеленог сертификата
                    </fo:block>
                    <fo:block-container margin-top="30px">
                        <fo:block start-indent="10pt" text-align="center" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                            У складу са одредбом Републике Србије о издавању дигиталног зеленог сертификата
                        </fo:block>
                        <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                            као потврде о извршеној вакцинацији против COVID-19, резултатима тестирања на заразну болест SARS-CoV-2 или опоравку од болести COVID-19, подносим захтев за издавање дигиталног зеленог сертификата.
                        </fo:block>
                    </fo:block-container>

                    <fo:block margin-top="30px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Подносилац захтева:
                    </fo:block>

                    <fo:block margin-top="15px">
                        <fo:inline-container inline-progression-dimension="19%">
                            <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                Име и презиме:
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="81%">
                            <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                <xsl:value-of select="//zahtjev:podnosilac/zaj:ime"/>&#160;<xsl:value-of select="//zahtjev:podnosilac/zaj:prezime"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block>
                        <fo:inline-container inline-progression-dimension="19%">
                            <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                Датум рођења:
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="81%">
                            <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                <xsl:value-of select="format-date(//zahtjev:podnosilac/zaj:datumRodjenja, '[D01].[M01].[Y0001].')"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block>
                        <fo:inline-container inline-progression-dimension="7%">
                            <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                Пол:
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="93%">
                            <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                <xsl:value-of select="//zahtjev:podnosilac/zaj:pol"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block>
                        <fo:inline-container inline-progression-dimension="43%">
                            <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                Јединствени матични број грађанина:
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="57%">
                            <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                <xsl:value-of select="//zahtjev:podnosilac/zaj:jmbg"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block>
                        <fo:inline-container inline-progression-dimension="16%">
                            <fo:block text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                Број пасоша:
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="84%">
                            <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                <xsl:value-of select="//zahtjev:podnosilac/zaj:brojPasosa"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block margin-top="15px" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Разлог за подношење захтева:
                        <fo:block text-align="left" font-weight="bold" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                            <xsl:value-of select="//zahtjev:razlog"/>
                        </fo:block>
                    </fo:block>

                    <fo:block margin-top="30px" text-align="center" font-family="Times New Roman" font-size="8.8px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        (навести што прецизнији разлога за подношење захтева за издавање дигиталног пасоша)
                    </fo:block>

                    <fo:block margin-top="50px" font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        У <xsl:value-of select="//zahtjev:lokacija"/>,
                    </fo:block>

                    <fo:block margin-top="15px" font-weight="bold" text-align="left" font-family="Times New Roman" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        дана <xsl:value-of select="format-date(//zahtjev:datum, '[D01].[M01].[Y0001].')"/> године
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>