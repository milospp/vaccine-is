<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:potvrda="http://www.ftn.uns.ac.rs/potvrda-o-vakcinaciji"
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

                    <fo:block margin-top="-30px">
                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block>
                                <fo:external-graphic src='url("/home/dalibor/Desktop/vaccine-is/vaccineis-backend/zajednicko/src/main/resources/static/images/Screenshot from 2022-02-08 02-50-25.png")' content-height="scale-to-fit" height="100px" content-width="1.3in" scaling="non-uniform"/>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block>
                                <fo:external-graphic src='url("/home/dalibor/Desktop/vaccine-is/vaccineis-backend/zajednicko/src/main/resources/static/images/Screenshot from 2022-02-08 02-49-37.png")'  content-height="scale-to-fit" height="100px" content-width="3.50in" scaling="non-uniform"/>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block text-align="left" font-family="DejaVu Sans" font-size="11.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Шифра потврде вакцинације: <fo:inline font-weight="bold"><xsl:value-of select="//potvrda:potvrdaVakcinacije/@sifra"/></fo:inline>
                    </fo:block>
                    <fo:block text-align="left" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                        Šifra potvrde / Confirmation code
                    </fo:block>

                    <fo:block margin-top="20px" text-align="center" font-weight="bold" font-family="Times New Roman" font-size="13.7px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        ПОТВРДА О ИЗВРШЕНОЈ ВАКЦИНАЦИЈИ ПРОТИВ COVID-19
                    </fo:block >
                    <fo:block text-align="center" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                        POTVRDA O IZVRŠENOJ VAKCINACIJI PROTIV COVID-19
                    </fo:block >
                    <fo:block text-align="center" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                        CONFIRMATION OF THE COVID-19 VACCINATION
                    </fo:block >

                    <fo:block margin-top="20px" text-align="left" font-weight="bold" font-family="DejaVu Sans" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Име и презиме: <fo:inline><xsl:value-of select="//potvrda:podaciVakcinisanog/zaj:ime"/></fo:inline>&#160;<fo:inline><xsl:value-of select="//potvrda:podaciVakcinisanog/zaj:prezime"/></fo:inline>
                    </fo:block>
                    <fo:block text-align="left" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                        Ime i prezime / First and Last Name
                    </fo:block>

                    <fo:block margin-top="20px" text-align="left" font-weight="bold" font-family="DejaVu Sans" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Датум рођења: <fo:inline><xsl:value-of select="format-date(//potvrda:podaciVakcinisanog/zaj:datumRodjenja, '[D01].[M01].[Y0001]')"/></fo:inline>
                    </fo:block>
                    <fo:block text-align="left" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                        Datum rođenja / Date Of Birth
                    </fo:block>

                    <fo:block margin-top="20px" text-align="left" font-weight="bold" font-family="DejaVu Sans" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Пол: <fo:inline><xsl:value-of select="//potvrda:podaciVakcinisanog/zaj:pol"/></fo:inline>
                    </fo:block>
                    <xsl:choose>
                        <xsl:when test="//potvrda:podaciVakcinisanog/zaj:pol = 'Мушко'">
                            <fo:block text-align="left" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                                Pol: Muško / Gender: Male
                            </fo:block>
                        </xsl:when>
                        <xsl:when test="//potvrda:podaciVakcinisanog/zaj:pol = 'Женско'">
                            <fo:block text-align="left" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                                Pol: Žensko / Gender: Female
                            </fo:block>
                            <div style="position:absolute;left:34px;top:306px" class="cls_003"><span class="cls_003">Pol: Žensko / Gender: Female</span></div>
                        </xsl:when>
                    </xsl:choose>

                    <fo:block margin-top="20px" text-align="left" font-weight="bold" font-family="DejaVu Sans" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        ЈМБГ: <fo:inline><xsl:value-of select="//potvrda:podaciVakcinisanog/zaj:jmbg"/></fo:inline>
                    </fo:block>
                    <fo:block text-align="left" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                        JMBG / Personal No.
                    </fo:block>

                    <fo:block-container>
                        <xsl:for-each select="//potvrda:podaciVakcinacije/potvrda:vakcinaPodaci">
                            <xsl:if test="zaj:brojDoze = '1'">
                                <fo:block margin-top="20px" text-align="left" font-weight="bold" font-family="DejaVu Sans" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Датум давања и број серије прве дозе вакцине: <fo:inline><xsl:value-of select="format-date(zaj:datumDavanjaDoze, '[D01].[M01].[Y0001]')"/></fo:inline>
                                    , серија: <fo:inline><xsl:value-of select="zaj:serijaDoze"/></fo:inline>
                                </fo:block>
                                <fo:block text-align="left" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                                    Datum vakcinacije / Vaccination Date
                                </fo:block>
                            </xsl:if>
                            <xsl:if test="zaj:brojDoze = '2'">
                                <fo:block margin-top="20px" text-align="left" font-weight="bold" font-family="DejaVu Sans" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Датум давања и број серије друге дозе вакцине: <fo:inline><xsl:value-of select="format-date(zaj:datumDavanjaDoze, '[D01].[M01].[Y0001]')"/></fo:inline>
                                    , серија: <fo:inline><xsl:value-of select="zaj:serijaDoze"/></fo:inline>
                                </fo:block>
                                <fo:block text-align="left" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                                    Datum druge vakcinacije / Second Vaccination Date
                                </fo:block>

                                <fo:block margin-top="20px" text-align="left" font-weight="bold" font-family="DejaVu Sans" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Здравствена установа која вакцинише: <fo:inline><xsl:value-of select="zaj:zdravstvenaUstanova"/></fo:inline>
                                </fo:block>
                                <fo:block text-align="left" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                                    Zdravstvena ustanova koja vakciniše / Health care institution of vaccination
                                </fo:block>

                                <fo:block margin-top="20px" text-align="left" font-weight="bold" font-family="DejaVu Sans" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Назив вакцине: <fo:inline><xsl:value-of select="zaj:nazivVakcine"/></fo:inline>
                                </fo:block>
                                <fo:block text-align="left" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                                    Naziv vakcine / Name of vaccine
                                </fo:block>
                            </xsl:if>
                        </xsl:for-each>

                        <fo:block margin-top="20px" text-align="right" font-weight="bold" font-family="DejaVu Sans" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                            Здравствена установа: Институт за јавно здравље Србије Др Милан Јовановић Батут
                        </fo:block>
                        <fo:block text-align="right" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                            Zdravstvena ustanova / Medical institution
                        </fo:block>

                        <fo:block margin-top="15px">
                            <fo:inline-container inline-progression-dimension="80%">
                                <fo:block margin-top="20px" text-align="left" font-family="DejaVu Sans" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                    Ова потврда важи без потписа и печата
                                </fo:block>
                                <fo:block text-align="left" font-family="DejaVu Sans" font-size="8.2px" color="rgb(128,128,128)" font-style="normal" text-decoration="none">
                                    Ova potvrda važi bez potpisa i pečata / This certificate is valid without signatures and seals
                                </fo:block>
                            </fo:inline-container>

                            <fo:inline-container inline-progression-dimension="20%">
                                <fo:block>
                                    <fo:external-graphic content-height="scale-to-fit" height="100px" content-width="1.3in" scaling="non-uniform">
                                        <xsl:attribute name="src">
                                            <xsl:value-of select="//potvrda:QRKod" />
                                        </xsl:attribute>
                                    </fo:external-graphic>
                                </fo:block>
                            </fo:inline-container>
                        </fo:block>

                    </fo:block-container>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>