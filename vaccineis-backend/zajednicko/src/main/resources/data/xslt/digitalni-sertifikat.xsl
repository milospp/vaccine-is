<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sertifikat="http://www.ftn.uns.ac.rs/digitalni-sertifikat"
                xmlns:zaj="http://www.ftn.uns.ac.rs/zajednicka"
                xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>ДИГИТАЛНИ ЗЕЛЕНИ СЕРТИФИКАТ</title>
                <style type="text/css">
                    span.cls_004{font-family:Arial,serif;font-size:14.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    div.cls_004{font-family:Arial,serif;font-size:14.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    span.cls_005{font-family:Arial,serif;font-size:12.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_005{font-family:Arial,serif;font-size:12.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_002{font-family:Arial,serif;font-size:9.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    div.cls_002{font-family:Arial,serif;font-size:9.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    span.cls_007{font-family:Arial,serif;font-size:9.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_007{font-family:Arial,serif;font-size:9.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_008{font-family:Arial,serif;font-size:9.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    div.cls_008{font-family:Arial,serif;font-size:9.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    span.cls_009{font-family:Arial,serif;font-size:10.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    div.cls_009{font-family:Arial,serif;font-size:10.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    span.cls_012{font-family:Arial,serif;font-size:8.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_012{font-family:Arial,serif;font-size:8.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_010{font-family:Times,serif;font-size:12.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_010{font-family:Times,serif;font-size:12.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    span.cls_011{font-family:Arial,serif;font-size:12.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    div.cls_011{font-family:Arial,serif;font-size:12.1px;color:rgb(0,0,0);font-weight:bold;font-style:normal;text-decoration: none}
                    span.cls_013{font-family:Arial,serif;font-size:10.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                    div.cls_013{font-family:Arial,serif;font-size:10.1px;color:rgb(0,0,0);font-weight:normal;font-style:normal;text-decoration: none}
                </style>
            </head>
            <body>

                <div style="position:absolute;left:50%;margin-left:-297px;top:0px;width:594px;height:841px;border-style:outset;overflow:hidden">
                    <div style="position:absolute;left:0px;top:0px">
                        <img width="594" height="841"/>
                    </div>
                    <img src="../../../static/images/Screenshot from 2022-02-08 11-17-12.png" style="position: absolute; left: 66px; top: 10px; height: 120px;"/>
                    <img style="position: absolute; right: 23px; top: 10px; height: 138px; width: 120px;">
                        <xsl:attribute name="src">
                            <xsl:value-of select="//sertifikat:qrKod"/>
                        </xsl:attribute>
                    </img>
                    <div style="position:absolute;left:188.66px;top:38.26px" class="cls_004"><span class="cls_004">ДИГИТАЛНИ ЗЕЛЕНИ СЕРТИФИКАТ</span></div>
                    <div style="position:absolute;left:194.82px;top:55.17px" class="cls_005"><span class="cls_005">Потврда о извршеној вакцинацији против</span></div>
                    <div style="position:absolute;left:213.09px;top:69.29px" class="cls_005"><span class="cls_005">COVID-19 и резултатима тестирања</span></div>
                    <div style="position:absolute;left:213.94px;top:83.23px" class="cls_004"><span class="cls_004">DIGITAL GREEN CERTIFICATE</span></div>
                    <div style="position:absolute;left:197.34px;top:99.59px" class="cls_005"><span class="cls_005">Certificate of vaccination against COVID-19</span></div>
                    <div style="position:absolute;left:269.74px;top:113.15px" class="cls_005"><span class="cls_005">and test results</span></div>
                    <div style="position:absolute;left:51.14px;top:134.92px" class="cls_002"><span class="cls_002">РЕПУБЛИКА СРБИЈА</span></div>
                    <div style="position:absolute;left:188.03px;top:133.74px" class="cls_007"><span class="cls_007">Овај документ је валидан искључиво уз лични документ са</span></div>
                    <div style="position:absolute;left:192.83px;top:142.74px" class="cls_007"><span class="cls_007">фотографијом / This document is valid only with a photo ID</span></div>
                    <div style="position:absolute;left:51.41px;top:145.26px" class="cls_002"><span class="cls_002">REPUBLIC OF SERBIA</span></div>
                    <div style="position:absolute;left:32.07px;top:181.69px" class="cls_008"><span class="cls_008">Број сертификата /</span></div>
                    <div style="position:absolute;left:306.43px;top:182.41px" class="cls_008"><span class="cls_008">Датум и време издавања сертификата /</span></div>
                    <div style="position:absolute;left:32.07px;top:193.20px" class="cls_008"><span class="cls_008">Certificate ID:</span></div>
                    <div style="position:absolute;left:115.35px;top:193.46px" class="cls_008"><span class="cls_008">
                        <xsl:value-of select="//sertifikat:zeleniSertifikat/@brojSertifikata"/>
                    </span></div>
                    <div style="position:absolute;left:306.43px;top:193.91px" class="cls_008"><span class="cls_008">Certificate issuing date and time:</span></div>
                    <div style="position:absolute;left:479.88px;top:194.47px" class="cls_008"><span class="cls_008">
                        <xsl:value-of select="format-dateTime(//sertifikat:zeleniSertifikat/@datumVrijemeIzdavanja, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                    </span></div>
                    <div style="position:absolute;left:31.81px;top:216.65px" class="cls_008"><span class="cls_008">Име и презиме / Name and surname:</span></div>
                    <div style="position:absolute;left:195.28px;top:217.12px" class="cls_007"><span class="cls_007">
                        <span style="margin-right:3px;"><xsl:value-of select="//sertifikat:podaciVakcinisanog/zaj:ime"/></span> <xsl:value-of select="//sertifikat:podaciVakcinisanog/zaj:prezime"/>
                    </span></div>
                    <div style="position:absolute;left:31.55px;top:245px" class="cls_008"><span class="cls_008">Датум рођења / Date of birth:</span></div>
                    <div style="position:absolute;left:165px;top:245.59px" class="cls_007"><span class="cls_007">
                        <xsl:value-of select="format-date(//sertifikat:podaciVakcinisanog/zaj:datumRodjenja, '[D01].[M01].[Y0001].')"/>
                    </span></div>
                    <div style="position:absolute;left:306.98px;top:216.65px" class="cls_008"><span class="cls_008">ЈМБГ / Personal No. / EBS:</span></div>
                    <div style="position:absolute;left:426px;top:216.67px" class="cls_007"><span class="cls_007">
                        <xsl:value-of select="//sertifikat:podaciVakcinisanog/zaj:jmbg"/>
                    </span></div>
                    <div style="position:absolute;left:306.33px;top:245px" class="cls_008"><span class="cls_008">Број пасоша / Passport No. :
                        <xsl:value-of select="//sertifikat:podaciVakcinisanog/zaj:brojPasosa"/></span></div>
                    <div style="position:absolute;left:32.60px;top:269.34px" class="cls_008"><span class="cls_008">Пол / Gender:</span></div>
                    <div style="position:absolute;left:100px;top:270.20px" class="cls_007"><span class="cls_007">
                        <xsl:value-of select="//sertifikat:podaciVakcinisanog/zaj:pol"/>
                        <xsl:choose>
                            <xsl:when test="//sertifikat:podaciVakcinisanog/zaj:pol = 'Мушко'">
                                <span> / Male</span>
                            </xsl:when>
                            <xsl:otherwise>
                                <span> / Female</span>
                            </xsl:otherwise>
                        </xsl:choose>
                    </span></div>
                    <div style="position:absolute;left:306.33px;top:269px" class="cls_008"><span class="cls_008">Издат од / Issued by:</span></div>

                    <table style="position:absolute;left:32.60px;top:290px; min-height: 70px; width: 520px; border-collapse: collapse;">
                        <tr>
                            <th colspan="5" style="border: 1px solid black; border-left: none; border-right: none;">
                                <div class="cls_009"><span class="cls_009">Вакцинација / Vaccination</span></div>
                            </th>
                        </tr>

                        <tr>
                            <xsl:for-each select="//sertifikat:vakcinacija/sertifikat:doza">
                                <xsl:choose>
                                    <xsl:when test="position() != last()">
                                        <td style="border-right: 1px solid black; padding: 4px;"><div class="cls_008"><span class="cls_008">Доза / Dose:
                                            <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:brojDoze"/></span></div></td>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <td style="padding: 4px;"><div class="cls_008"><span class="cls_008">Доза / Dose:
                                            <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:brojDoze"/></span></div></td>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <xsl:for-each select="//sertifikat:vakcinacija/sertifikat:doza">
                                <xsl:choose>
                                    <xsl:when test="position() != last()">
                                        <td style="border-right: 1px solid black; padding: 4px;"><div class="cls_008"><span class="cls_008">Тип / Type: <span class="cls_007">
                                            <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:nazivVakcine"/>
                                        </span></span></div></td>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <td style="padding: 4px;"><div class="cls_008"><span class="cls_008">Тип / Type: <span class="cls_007">
                                            <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:nazivVakcine"/>
                                        </span></span></div></td>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <xsl:for-each select="//sertifikat:vakcinacija/sertifikat:doza">
                                <xsl:choose>
                                    <xsl:when test="position() != last()">
                                        <td style="border-right: 1px solid black; padding: 4px;"><div class="cls_008"><span class="cls_008">Произвођач и серија / Manufacturer and batch number: <span class="cls_007">
                                            <span style="margin-right:3px;"><xsl:value-of select="sertifikat:proizvodjac"/></span> <xsl:value-of select="sertifikat:serijaVakcine"/>
                                        </span></span></div></td>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <td style="padding: 4px;"><div class="cls_008"><span class="cls_008">Произвођач и серија / Manufacturer and batch number: <span class="cls_007">
                                            <span style="margin-right:3px;"><xsl:value-of select="sertifikat:proizvodjac"/></span> <xsl:value-of select="sertifikat:serijaVakcine"/>
                                        </span></span></div></td>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <xsl:for-each select="//sertifikat:vakcinacija/sertifikat:doza">
                                <xsl:choose>
                                    <xsl:when test="position() != last()">
                                        <td style="border-right: 1px solid black; padding: 4px;"><div class="cls_008"><span class="cls_008">Датум / Date: <span class="cls_007">
                                            <xsl:value-of select="format-date(sertifikat:vakcinaPodaci/zaj:datumDavanjaDoze, '[D01].[M01].[Y0001]')"/>
                                        </span></span></div></td>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <td style="padding: 4px;"><div class="cls_008"><span class="cls_008">Датум / Date: <span class="cls_007">
                                            <xsl:value-of select="format-date(sertifikat:vakcinaPodaci/zaj:datumDavanjaDoze, '[D01].[M01].[Y0001]')"/>
                                        </span></span></div></td>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:for-each>
                        </tr>
                        <tr>
                            <xsl:for-each select="//sertifikat:vakcinacija/sertifikat:doza">
                                <xsl:choose>
                                    <xsl:when test="position() != last()">
                                        <td style="border-right: 1px solid black; padding: 4px;"><div class="cls_008"><span class="cls_008">Здравствена установа / Health care institution: <span class="cls_012">
                                            <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:zdravstvenaUstanova"/>
                                        </span></span></div></td>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <td style="padding: 4px;"><div class="cls_008"><span class="cls_008">Здравствена установа / Health care institution: <span class="cls_012">
                                            <xsl:value-of select="sertifikat:vakcinaPodaci/zaj:zdravstvenaUstanova"/>
                                        </span></span></div></td>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:for-each>
                        </tr>
                    </table>

                    <table style="border-collapse: collapse; border: 1px solid black; border-left: none; border-right: none; position:absolute;left:32.60px;top:430px; min-height: 70px; width: 520px;">
                        <tr>
                            <th style="border-right: 1px solid black; border-bottom: 1px solid black; padding: 3px;">
                                <div class="cls_008"><span class="cls_008">SARS-CoV-2 RT</span></div>
                                <div class="cls_008"><span class="cls_008">Real-time PCR</span></div>
                            </th>
                            <th style="border-right: 1px solid black; border-bottom: 1px solid black; padding: 3px;">
                                <div class="cls_008"><span class="cls_008">SARS-CoV-2 Ag-RDT</span></div>
                                <div class="cls_008"><span class="cls_008">(Antigen Rapid Detection test)</span></div>
                            </th>
                            <th style="padding: 3px; border-bottom: 1px solid black;">
                                <div class="cls_008"><span class="cls_008">SARS-CoV-2 S-Protein (RBD)</span></div>
                                <div class="cls_008"><span class="cls_008">Immunoglobulin G (IgG) test</span></div>
                            </th>
                        </tr>

                        <tr>
                            <td style="border-right: 1px solid black; padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Врста узорка / Sample type:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:vrstaUzorka"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="border-right: 1px solid black; padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Врста узорка / Sample type:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:vrstaUzorka"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Врста узорка / Sample type:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:vrstaUzorka"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                        </tr>

                        <tr>
                            <td style="border-right: 1px solid black; padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Произвођач теста / Test manufacturer:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:proizvodjacTesta"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="border-right: 1px solid black; padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Произвођач теста / Test manufacturer:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:proizvodjacTesta"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Произвођач теста / Test manufacturer:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:proizvodjacTesta"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                        </tr>

                        <tr>
                            <td style="border-right: 1px solid black; padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Датум и време узорковања /</span></div>
                                <div class="cls_008"><span class="cls_008">Date and time of sampling:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:datumVremeUzorkovanja, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="border-right: 1px solid black; padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Датум и време узорковања /</span></div>
                                <div class="cls_008"><span class="cls_008">Date and time of sampling:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:datumVremeUzorkovanja, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Датум и време узорковања /</span></div>
                                <div class="cls_008"><span class="cls_008">Date and time of sampling:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:datumVremeUzorkovanja, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                        </tr>

                        <tr>
                            <td style="border-right: 1px solid black; padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Датум и време издавања резултата /</span></div>
                                <div class="cls_008"><span class="cls_008">Date and time of result:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:datumVremeRezultata, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="border-right: 1px solid black; padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Датум и време издавања резултата /</span></div>
                                <div class="cls_008"><span class="cls_008">Date and time of result:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:datumVremeRezultata, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="padding: 3px;">
                                <div class="cls_008"><span class="cls_008">Датум и време издавања резултата /</span></div>
                                <div class="cls_008"><span class="cls_008">Date and time of result:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="format-dateTime(//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:datumVremeRezultata, '[D01].[M01].[Y0001].  [h01]:[m01]')"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                        </tr>

                        <tr>
                            <td style="border-right: 1px solid black; padding: 3px; background-color: grey;">
                                <div class="cls_008"><span class="cls_008">Резултат / Result:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:rezultat"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="border-right: 1px solid black; padding: 3px; background-color: grey;">
                                <div class="cls_008"><span class="cls_008">Резултат / Result:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:rezultat"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="padding: 3px; background-color: grey;">
                                <div class="cls_008"><span class="cls_008">Резултат / Result:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:rezultat"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                        </tr>

                        <tr>
                            <td style="border-right: 1px solid black; padding: 3px; background-color: grey;">
                                <div class="cls_008"><span class="cls_008">Лабораторија / Laboratory:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 RT Real-time PCR']/sertifikat:laboratorija"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="border-right: 1px solid black; padding: 3px; background-color: grey;">
                                <div class="cls_008"><span class="cls_008">Лабораторија / Laboratory:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 Ag-RDT (Antigen Rapid Detection test)']/sertifikat:laboratorija"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td style="padding: 3px; background-color: grey;">
                                <div class="cls_008"><span class="cls_008">Лабораторија / Laboratory:</span></div>
                                <xsl:choose>
                                    <xsl:when test="//sertifikat:testovi[sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']]">
                                        <div class="cls_007"><span class="cls_007">
                                            <xsl:value-of select="//sertifikat:testovi/sertifikat:test[sertifikat:tipTesta = 'SARS-CoV-2 S-Protein (RBD) Immunoglobulin G (IgG) test']/sertifikat:laboratorija"/>
                                        </span></div>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <div class="cls_007"><span class="cls_007">N/A</span></div>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                        </tr>
                    </table>


                    <div style="position:absolute;left:79.29px;top:670px" class="cls_011"><span class="cls_011">Потврда о прележаној болести COVID-19 / Confirmation of COVID-19 recovery</span></div>
                    <div style="position:absolute;left:32.98px;top:690px" class="cls_008"><span class="cls_008">Датум позитивног теста - лабораторија /</span></div>
                    <div style="position:absolute;left:32.98px;top:705px" class="cls_008"><span class="cls_008">Date of positive test - laboratory:</span></div>
                    <div style="position:absolute;left:209.09px;top:705px" class="cls_012"><span class="cls_012">N/A</span></div>
                    <div style="position:absolute;left:350.79px;top:720px" class="cls_008"><span class="cls_008">Дигитални потпис / Digitally signed by:</span></div>
                    <div style="position:absolute;left:110.16px;top:730px" class="cls_009"><span class="cls_009">Сертификат издаје:</span></div>
                    <div style="position:absolute;left:110.16px;top:743px" class="cls_013"><span class="cls_013">Институт за јавно здравље Србије</span></div>
                    <div style="position:absolute;left:110.16px;top:755px" class="cls_013"><span class="cls_013">"Др Милан Јовановић Батут"</span></div>
                    <div style="position:absolute;left:110.16px;top:772px" class="cls_009"><span class="cls_009">Certificate issued by:</span></div>
                    <div style="position:absolute;left:110.16px;top:784px" class="cls_013"><span class="cls_013">Institute of Public Health of Serbia</span></div>
                    <div style="position:absolute;left:110.16px;top:796px" class="cls_013"><span class="cls_013">"Dr Milan Jovanović Batut"</span></div>
                    <img src="../../../static/images/Screenshot from 2022-02-08 11-18-10.png" style="position: absolute; left: 32px; top: 730px; height: 75px;"/>
                    <img src="../../../static/images/Screenshot from 2022-02-08 11-27-59.png" style="position: absolute; left: 360px; top: 735px; height: 80px;"/>
                </div>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>