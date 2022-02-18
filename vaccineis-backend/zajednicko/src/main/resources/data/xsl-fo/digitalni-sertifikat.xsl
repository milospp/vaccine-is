<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sertifikat="http://www.ftn.uns.ac.rs/digitalni-sertifikat"
                xmlns:zaj="http://www.ftn.uns.ac.rs/zajednicka"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output indent="yes"/>
    <xsl:strip-space elements="*"/>

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="sertifikat-page" page-width="595px" page-height="960px">
                    <fo:region-body margin="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="sertifikat-page">
                <fo:flow flow-name="xsl-region-body">

                    <fo:block margin-top="-30px">
                        <fo:inline-container inline-progression-dimension="15%">
                            <fo:block margin-left="10px">
                                <fo:external-graphic src='url("/home/dalibor/Desktop/vaccine-is/vaccineis-backend/zajednicko/src/main/resources/static/images/Screenshot from 2022-02-08 11-17-12.png")' height="20px" content-width="1.0in" scaling="non-uniform"/>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="65%">
                            <fo:block text-align="center" font-weight="bold" font-family="Arial" font-size="14.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">ДИГИТАЛНИ ЗЕЛЕНИ СЕРТИФИКАТ</fo:block>
                            <fo:block text-align="center" font-weight="normal" font-family="Arial" font-size="12.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Потврда о извршеној вакцинацији против</fo:block>
                            <fo:block text-align="center" font-weight="normal" font-family="Arial" font-size="12.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">COVID-19 и резултатима тестирања</fo:block>
                            <fo:block text-align="center" font-weight="bold" font-family="Arial" font-size="14.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">DIGITAL GREEN CERTIFICATE</fo:block>
                            <fo:block text-align="center" font-weight="normal" font-family="Arial" font-size="12.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Certificate of vaccination against COVID-19</fo:block>
                            <fo:block text-align="center" font-weight="normal" font-family="Arial" font-size="12.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">and test results</fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="20%">
                            <fo:block margin-left="-10px">
                                <fo:external-graphic height="20px" content-width="1.7in" scaling="non-uniform">
                                    <xsl:attribute name="src">
                                        <xsl:value-of select="//sertifikat:qrKod" />
                                    </xsl:attribute>
                                </fo:external-graphic>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block>
                        <fo:inline-container inline-progression-dimension="30%">
                            <fo:block margin-top="30px" text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">РЕПУБЛИКА СРБИЈА</fo:block>
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">REPUBLIC OF SERBIA</fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="70%">
                            <fo:block text-align="center" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Овај документ је валидан искључиво уз лични документ са фотографијом</fo:block>
                            <fo:block text-align="center" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none"> This document is valid only with a photo ID</fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block>
                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block margin-top="15px" text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Број сертификата /</fo:block>
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Certificate ID:
                                <fo:inline> <xsl:value-of select="//sertifikat:zeleniSertifikat/@brojSertifikata"/> </fo:inline>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Датум и време издавања сертификата /</fo:block>
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Certificate issuing date and time:
                                <fo:inline> <xsl:value-of select="format-dateTime(//sertifikat:zeleniSertifikat/@datumVrijemeIzdavanja, '[D01].[M01].[Y0001].  [h01]:[m01]')"/> </fo:inline>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block>
                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block margin-top="5px" text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Име и презиме / Name and surname: </fo:block>
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                <fo:inline> <xsl:value-of select="//sertifikat:podaciVakcinisanog/zaj:ime"/> </fo:inline>&#160;
                                <fo:inline> <xsl:value-of select="//sertifikat:podaciVakcinisanog/zaj:prezime"/> </fo:inline>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">ЈМБГ / Personal No. / EBS:</fo:block>
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                <fo:inline> <xsl:value-of select="format-date(//sertifikat:podaciVakcinisanog/zaj:datumRodjenja, '[D01].[M01].[Y0001].')"/> </fo:inline>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block>
                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block margin-top="5px" text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Датум рођења / Date of birth: </fo:block>
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                <xsl:value-of select="format-date(//sertifikat:podaciVakcinisanog/zaj:datumRodjenja, '[D01].[M01].[Y0001].')"/>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Број пасоша / Passport No. :</fo:block>
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                <fo:inline> <xsl:value-of select="//sertifikat:podaciVakcinisanog/zaj:brojPasosa"/> </fo:inline>
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:block>
                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block margin-top="5px" text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Пол / Gender:
                                <fo:inline><xsl:value-of select="//sertifikat:podaciVakcinisanog/zaj:pol"/></fo:inline> /
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:podaciVakcinisanog/zaj:pol = 'Мушко'">
                                        <fo:inline> Male </fo:inline>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <fo:inline> Female </fo:inline>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Издат од / Issued by:</fo:block>
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                    <fo:table font-family="Arial" border="1px" border-collapse="collapse" margin-top="10px">
                        <xsl:for-each select="//sertifikat:vakcinacija/sertifikat:doza">
                            <fo:table-column/>
                        </xsl:for-each>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell padding="2px" border="1px solid black" border-left="none" border-right="none" number-columns-spanned="2">
                                    <fo:block text-align="center" font-family="Arial" font-weight="bold" font-size="10.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Вакцинација / Vaccination</fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <xsl:for-each select="//sertifikat:vakcinacija/sertifikat:doza">
                                    <xsl:choose>
                                        <xsl:when test="position() != last()">
                                            <fo:table-cell border-right="1px solid black" padding="4px" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="left">Доза / Dose:
                                                    <fo:inline font-weight="normal"> <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:brojDoze"/> </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:table-cell padding="4px" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="left">Доза / Dose:
                                                    <fo:inline font-weight="normal"> <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:brojDoze"/> </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </xsl:for-each>
                            </fo:table-row>

                            <fo:table-row>
                                <xsl:for-each select="//sertifikat:vakcinacija/sertifikat:doza">
                                    <xsl:choose>
                                        <xsl:when test="position() != last()">
                                            <fo:table-cell border-right="1px solid black" padding="4px" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="left">Тип / Type:
                                                    <fo:inline font-weight="normal"> <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:nazivVakcine"/> </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:table-cell padding="4px" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="left">Тип / Type:
                                                    <fo:inline font-weight="normal"> <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:nazivVakcine"/> </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </xsl:for-each>
                            </fo:table-row>

                            <fo:table-row>
                                <xsl:for-each select="//sertifikat:vakcinacija/sertifikat:doza">
                                    <xsl:choose>
                                        <xsl:when test="position() != last()">
                                            <fo:table-cell border-right="1px solid black" padding="4px" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="left">Произвођач и серија / Manufacturer and batch number:
                                                    <fo:inline font-weight="normal"> <xsl:value-of select="sertifikat:proizvodjac"/> </fo:inline>,
                                                    <fo:inline font-weight="normal"> <xsl:value-of select="sertifikat:serijaVakcine"/> </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:table-cell padding="4px" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="left">Произвођач и серија / Manufacturer and batch number:
                                                    <fo:inline font-weight="normal"> <xsl:value-of select="sertifikat:proizvodjac"/> </fo:inline>,
                                                    <fo:inline font-weight="normal"> <xsl:value-of select="sertifikat:serijaVakcine"/> </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </xsl:for-each>
                            </fo:table-row>

                            <fo:table-row>
                                <xsl:for-each select="//sertifikat:vakcinacija/sertifikat:doza">
                                    <xsl:choose>
                                        <xsl:when test="position() != last()">
                                            <fo:table-cell border-right="1px solid black" padding="4px" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="left">Датум / Date:
                                                    <fo:inline font-weight="normal"> <xsl:value-of select="format-date(sertifikat:vakcinaPodaci/zaj:datumDavanjaDoze, '[D01].[M01].[Y0001]')"/> </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:table-cell padding="4px" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="left">Датум / Date:
                                                    <fo:inline font-weight="normal">  <xsl:value-of select="format-date(sertifikat:vakcinaPodaci/zaj:datumDavanjaDoze, '[D01].[M01].[Y0001]')"/> </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </xsl:for-each>
                            </fo:table-row>

                            <fo:table-row>
                                <xsl:for-each select="//sertifikat:vakcinacija/sertifikat:doza">
                                    <xsl:choose>
                                        <xsl:when test="position() != last()">
                                            <fo:table-cell border-right="1px solid black" padding="4px" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="left">Здравствена установа / Health care institution:
                                                    <fo:inline font-weight="normal"> <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:zdravstvenaUstanova"/> </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:table-cell padding="4px" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <fo:block text-align="left">Здравствена установа / Health care institution:
                                                    <fo:inline font-weight="normal">  <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:zdravstvenaUstanova"/> </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </xsl:for-each>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:table font-family="Arial" border="1px" border-collapse="collapse" margin-top="2px">
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell padding="4px" border="1px solid black" border-left="none">
                                    <fo:block text-align="center" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">SARS-CoV-2 RT</fo:block>
                                    <fo:block text-align="center" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Real-time PCR</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="4px" border="1px solid black">
                                    <fo:block text-align="center" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">SARS-CoV-2 Ag-RDT</fo:block>
                                    <fo:block text-align="center" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">(Antigen Rapid Detection test)</fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="4px" border="1px solid black" border-right="none">
                                    <fo:block text-align="center" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">SARS-CoV-2 S-Protein (RBD)</fo:block>
                                    <fo:block text-align="center" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Immunoglobulin G (IgG) test</fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell padding="2px" border-right="1px solid black">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Врста узорка / Sample type:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                            <fo:block text-align="center" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:vrstaUzorka"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px" border-right="1px solid black">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Врста узорка / Sample type:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:vrstaUzorka"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Врста узорка / Sample type:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:vrstaUzorka"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell padding="2px" border-right="1px solid black">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Произвођач теста / Test manufacturer:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                            <fo:block text-align="center" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:proizvodjacTesta"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px" border-right="1px solid black">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Произвођач теста / Test manufacturer:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:proizvodjacTesta"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Произвођач теста / Test manufacturer:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:proizvodjacTesta"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell padding="2px" border-right="1px solid black">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Датум и време узорковања / Date and time of sampling:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                            <fo:block text-align="center" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:datumVremeUzorkovanja, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px" border-right="1px solid black">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Датум и време узорковања / Date and time of sampling:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:datumVremeUzorkovanja, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Датум и време узорковања / Date and time of sampling:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:datumVremeUzorkovanja, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell padding="2px" border-right="1px solid black">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Датум и време издавања резултата / Date and time of result:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                            <fo:block text-align="center" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:datumVremeRezultata, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px" border-right="1px solid black">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Датум и време издавања резултата / Date and time of result:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:datumVremeRezultata, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Датум и време издавања резултата / Date and time of result:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:datumVremeRezultata, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell padding="2px" border-right="1px solid black" background-color="grey">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Резултат / Result:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                            <fo:block text-align="center" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:rezultat"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px" border-right="1px solid black" background-color="grey">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Резултат / Result:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:rezultat"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px" background-color="grey">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Резултат / Result:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:rezultat"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                            </fo:table-row>

                            <fo:table-row border-bottom="1px solid black">
                                <fo:table-cell padding="2px" border-right="1px solid black" background-color="grey">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Лабораторија / Laboratory:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                            <fo:block text-align="center" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:laboratorija"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px" border-right="1px solid black" background-color="grey">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Лабораторија / Laboratory:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:laboratorija"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                                <fo:table-cell padding="2px" background-color="grey">
                                    <fo:block text-align="left" font-family="Arial" font-weight="bold" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">Лабораторија / Laboratory:</fo:block>
                                    <xsl:choose>
                                        <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                                <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:laboratorija"/>
                                            </fo:block>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:block text-align="left" font-family="Arial" font-weight="normal" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">N/A</fo:block>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block margin-top="10px" text-align="center" font-weight="bold" font-family="Arial" font-size="12.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Потврда о прележаној болести COVID-19 / Confirmation of COVID-19 recovery
                    </fo:block>
                    <fo:block margin-top="10px" text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Датум позитивног теста - лабораторија / Date of positive test - laboratory:     N/A
                    </fo:block>

                    <fo:block margin-top="10px" margin-left="250px" text-align="left" font-weight="bold" font-family="Arial" font-size="9.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                        Дигитални потпис / Digitally signed by:
                    </fo:block>

                    <fo:block margin-top="10px">
                        <fo:inline-container inline-progression-dimension="10%">
                            <fo:block>
                                <fo:external-graphic src='url("/home/dalibor/Desktop/vaccine-is/vaccineis-backend/zajednicko/src/main/resources/static/images/Screenshot from 2022-02-08 11-18-10.png")' height="10px" content-width="0.8in" />
                            </fo:block>
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="40%" margin-left="15px">
                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                Сертификат издаје:
                            </fo:block >
                            <fo:block text-align="left" font-family="Arial" font-size="10.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                Институт за јавно здравље Србије
                            </fo:block >
                            <fo:block text-align="left" font-family="Arial" font-size="10.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                "Др Милан Јовановић Батут"
                            </fo:block >

                            <fo:block text-align="left" font-weight="bold" font-family="Arial" font-size="10.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                Certificate issued by:
                            </fo:block >
                            <fo:block text-align="left" font-family="Arial" font-size="10.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                Institute of Public Health of Serbia
                            </fo:block >
                            <fo:block text-align="left" font-family="Arial" font-size="10.1px" color="rgb(0,0,0)" font-style="normal" text-decoration="none">
                                "Dr Milan Jovanović Batut"
                            </fo:block >
                        </fo:inline-container>

                        <fo:inline-container inline-progression-dimension="50%">
                            <fo:block margin-left="40px">
                                <fo:external-graphic src='url("/home/dalibor/Desktop/vaccine-is/vaccineis-backend/zajednicko/src/main/resources/static/images/Screenshot from 2022-02-08 11-27-59.png")' height="10px" content-width="2.8in" />
                            </fo:block>
                        </fo:inline-container>
                    </fo:block>

                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>