package zy.blue7.jsontoobj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import zy.blue7.jsontoobj.model.helpers.MyFastJsonParser;
import zy.blue7.jsontoobj.model.interfaces.IMyJsonParser;
import zy.blue7.jsontoobj.utils.FileUtils;
import zy.blue7.jsontoobj.utils.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class JsontoobjApplicationTests {

    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private  Environment environment;

    @Autowired
    private IMyJsonParser myJsonParser;

    @Autowired
    private MyFastJsonParser myFastJsonParser;

    String strJson="";
    String strParseArr="";
    String strParseArrs="";
    {

         strParseArrs="{\n" +
                 "    \"f\": [\n" +
                 "        [\n" +
                 "            \"dasd\"\n" +
                 "        ]\n" +
                 "    ]\n" +
                 "}";
         strParseArr="{\n" +
                 "    \"a\":[\n" +
                 "        [\n" +
                 "            {\n" +
                 "                \"aa\":\"21\"\n" +
                 "            }\n" +
                 "        ],[\n" +
                 "            {\n" +
                 "                \"aa\":\"232\"\n" +
                 "            }\n" +
                 "        ]\n" +
                 "    ],\n" +
                 "    \"b\":[\n" +
                 "        {\n" +
                 "            \"bb\":90\n" +
                 "        }\n" +
                 "    ],\n" +
                 "    \"f\":[[\n" +
                 "        \"dasd\"\n" +
                 "    ]],\n" +
                 "    \"c\":12,\n" +
                 "    \"d\":true,\n" +
                 "    \"e\":34.2\n" +
                 "}";
         strJson="{\n" +
                 "    \"Header\": {\n" +
                 "        \"@Action\": \"CREATE\",\n" +
                 "        \"DocumentTimestamp\": \"2020-07-29T16:24:27Z\",\n" +
                 "        \"DocumentUUID\": \"517f78f3-89b1-4efb-848b-797fd0d6b906\"\n" +
                 "    },\n" +
                 "    \"ConsumerBestRecord\": {\n" +
                 "        \"@RecordUUID\": \"cfc7bef7-f53f-45a6-ad1d-a106afa255fa\",\n" +
                 "        \"BestRecord\": {\n" +
                 "            \"SourceSystemList\": {\n" +
                 "                \"SourceSystem\": [\n" +
                 "                    {\n" +
                 "                        \"@Code\": \"undausundcbiund\",\n" +
                 "                        \"SourceTimestamp\": \"2014-05-06T00:00:00Z\",\n" +
                 "                        \"AffiliateCode\": \"CHN\",\n" +
                 "                        \"MarketCode\": \"AUS\",\n" +
                 "                        \"DivisionCode\": \"01\",\n" +
                 "                        \"BrandCode\": \"11\",\n" +
                 "                        \"ConsumerId\": \"44610\",\n" +
                 "                        \"TerminalId\": \"1210\"\n" +
                 "                    }\n" +
                 "                ]\n" +
                 "            },\n" +
                 "            \"Attributes\": {\n" +
                 "                \"RecordTimestamp\": \"2020-07-29T16:03:52Z\",\n" +
                 "                \"SourceTimestamp\": \"2017-04-06T00:00:00Z\",\n" +
                 "                \"CSRInstanceCode\": \"csrcdpapc\",\n" +
                 "                \"CSRInstanceDescription\": \"CSR CDP — APAC\",\n" +
                 "                \"UniversalKey\": \"ffc91d39-73d4-4010-86e4-f778d93342bd\",\n" +
                 "                \"RecognitionServiceCode\": \"cnsmtlndmdm\"\n" +
                 "            },\n" +
                 "            \"PersonalData\": {\n" +
                 "                \"RegDate\": \"2014-05-06T00:00:00Z\",\n" +
                 "                \"RegTouchPointSourceSystem\": {\n" +
                 "                    \"SourceTimestamp\": \"2014-05-06T00:00:00Z\",\n" +
                 "                    \"AffiliateCode\": \"CHN\",\n" +
                 "                    \"MarketCode\": \"AUS\",\n" +
                 "                    \"DivisionCode\": \"CHN\",\n" +
                 "                    \"BrandCode\": \"01\",\n" +
                 "                    \"TouchPointCode\": \"321354\"\n" +
                 "                },\n" +
                 "                \"LastUpdateTouchPointSourceSystem\": {\n" +
                 "                    \"SourceTimestamp\": \"2017-04-06T00:00:00Z\",\n" +
                 "                    \"AffiliateCode\": \"AUS\",\n" +
                 "                    \"MarketCode\": \"AUS\",\n" +
                 "                    \"DivisionCode\": \"AUS\",\n" +
                 "                    \"BrandCode\": \"01\",\n" +
                 "                    \"TouchPointCode\": \"321354\"\n" +
                 "                },\n" +
                 "                \"RegPersonnelSourceSystem\": {\n" +
                 "                    \"SourceTimestamp\": \"2019-01-07 17:25:21.0\",\n" +
                 "                    \"AffiliateCode\": \"AUS\",\n" +
                 "                    \"MarketCode\": \"AUS\",\n" +
                 "                    \"DivisionCode\": \"01\",\n" +
                 "                    \"BrandCode\": \"1\",\n" +
                 "                    \"PersonnelCode\": \"43\"\n" +
                 "                },\n" +
                 "                \"EformRegFlag\": true,\n" +
                 "                \"NameGenderCode\": \"fml\",\n" +
                 "                \"GenderCode\": \"fml\",\n" +
                 "                \"CivilStatusCode\": \"mrr\",\n" +
                 "                \"ConsumerCountryCode_ISO3\": \"CHN\",\n" +
                 "                \"SpokenLanguageCode\": \"eng\",\n" +
                 "                \"WrittenLanguageCode\": \"englatn\",\n" +
                 "                \"BirthDay\": 20,\n" +
                 "                \"BirthMonth\": 1,\n" +
                 "                \"BirthYear\": 1987,\n" +
                 "                \"EstimatedBirthYear\": 1987,\n" +
                 "                \"NameFilledFlag\": true\n" +
                 "            },\n" +
                 "            \"ProgramList\": {\n" +
                 "                \"Program\": [\n" +
                 "                    {\n" +
                 "                        \"ApplicationTouchPointCode\": \"23\",\n" +
                 "                        \"ConsumerGroup\": \"High Valued Consumer\",\n" +
                 "                        \"ProgramTypeCode\": \"chn01rntclb\",\n" +
                 "                        \"ProgramTypeDescription\": \"China / Estēe Lauder — Re-Nutriv Club\",\n" +
                 "                        \"ProgramLevelCode\": \"chn01rntclb\",\n" +
                 "                        \"ProgramLevelDescription\": \"China / Estēe Lauder — Re-Nutriv Club\",\n" +
                 "                        \"ProgramSystemIDCode\": \"chn01rntclb\",\n" +
                 "                        \"ProgramSystemIDDescription\": \"China / Estēe Lauder — Re-Nutriv Club\",\n" +
                 "                        \"MembershipNum\": \"121012101210\",\n" +
                 "                        \"CardNum\": \"121012101210\",\n" +
                 "                        \"StartTimestamp\": \"2019-02-11T12:00:00\",\n" +
                 "                        \"EndTimestamp\": \"2020-02-11T12:00:00\",\n" +
                 "                        \"PointsAcquired\": \"150\",\n" +
                 "                        \"PointsRedeemed\": \"100\",\n" +
                 "                        \"InitialQuota\": \"150\",\n" +
                 "                        \"AvailableQuota\": \"50\"\n" +
                 "                    }\n" +
                 "                ]\n" +
                 "            },\n" +
                 "            \"CustomAttributeList\": {\n" +
                 "                \"CustomAttribute\": [\n" +
                 "                    {\n" +
                 "                        \"@Name\": \"CustomerId\",\n" +
                 "                        \"@Value\": \"1210\"\n" +
                 "                    },\n" +
                 "                    {\n" +
                 "                        \"@Name\": \"Skintype\",\n" +
                 "                        \"@Value\": \"Dry\"\n" +
                 "                    }\n" +
                 "                ]\n" +
                 "            },\n" +
                 "            \"CalculatedBusinessVariables\": {\n" +
                 "                \"Segment\": {\n" +
                 "                    \"@Code\": \"\",\n" +
                 "                    \"Description\": \"\"\n" +
                 "                },\n" +
                 "                \"Decile\": 12,\n" +
                 "                \"ValueGroup\": {\n" +
                 "                    \"@Code\": \"\",\n" +
                 "                    \"Description\": \"\"\n" +
                 "                },\n" +
                 "                \"RecencySegment\": {\n" +
                 "                    \"@Code\": \"\",\n" +
                 "                    \"Description\": \"\",\n" +
                 "                    \"Active\": true\n" +
                 "                },\n" +
                 "                \"PurchaseSummary\": {\n" +
                 "                    \"TotalPurchaseNumber\": 12,\n" +
                 "                    \"R12MPurchaseNumber\": 12,\n" +
                 "                    \"Currency\": {\n" +
                 "                        \"CurrencyCode\": \"asd\"\n" +
                 "                    },\n" +
                 "                    \"GrossRevenueNoVAT\": 12.12,\n" +
                 "                    \"GrossRevenueVAT\": 12.23,\n" +
                 "                    \"NetRevenueNoVAT\": 12.14,\n" +
                 "                    \"NetRevenueVAT\": 12.13\n" +
                 "                }\n" +
                 "            }\n" +
                 "        },\n" +
                 "        \"DerivedBestRecordList\": {\n" +
                 "            \"DerivedBestRecord\": [\n" +
                 "                {\n" +
                 "                    \"@Level\": \"2\",\n" +
                 "                    \"SourceSystemList\": {\n" +
                 "                        \"SourceSystem\": [\n" +
                 "                            {\n" +
                 "                                \"@Code\": \"undchnundrtlpround\",\n" +
                 "                                \"SourceTimestamp\": \"2019-02-11T12:00:00\",\n" +
                 "                                \"AffiliateCode\": \"CHN\",\n" +
                 "                                \"MarketCode\": \"CHN\",\n" +
                 "                                \"DivisionCode\": \"01\",\n" +
                 "                                \"BrandCode\": \"01\",\n" +
                 "                                \"ConsumerId\": \"1210\",\n" +
                 "                                \"TerminaId\": \"1210\"\n" +
                 "                            }\n" +
                 "                        ]\n" +
                 "                    },\n" +
                 "                    \"Attributes\": {\n" +
                 "                        \"RecordTimestamp\": \"2020-07-29T16:07:48Z\",\n" +
                 "                        \"SourceTimestamp\": \"2017-04-06T00:00:00Z\",\n" +
                 "                        \"MarketCode\": \"AUS\",\n" +
                 "                        \"BrandCode\": \"01\",\n" +
                 "                        \"RetailerHierarchyCode\": \"01\",\n" +
                 "                        \"TouchPointCode\": \"01\"\n" +
                 "                    },\n" +
                 "                    \"PersonalData\": {\n" +
                 "                        \"RegDate\": \"2017-04-06T00:00:00Z\",\n" +
                 "                        \"RegTouchPointSourceSystem\": {\n" +
                 "                            \"SourceTimestamp\": \"2017-04-06T00:00:00Z\",\n" +
                 "                            \"AffiliateCode\": \"CHN\",\n" +
                 "                            \"MarketCode\": \"AUS\",\n" +
                 "                            \"DivisionCode\": \"CHN\",\n" +
                 "                            \"BrandCode\": \"01\",\n" +
                 "                            \"TouchPointCode\": \"321354\"\n" +
                 "                        },\n" +
                 "                        \"LastUpdateTouchPointSourceSystem\": {\n" +
                 "                            \"SourceTimestamp\": \"2017-04-06T00:00:00Z\",\n" +
                 "                            \"AffiliateCode\": \"AUS\",\n" +
                 "                            \"MarketCode\": \"AUS\",\n" +
                 "                            \"DivisionCode\": \"AUS\",\n" +
                 "                            \"BrandCode\": \"01\",\n" +
                 "                            \"TouchPointCode\": \"321354\"\n" +
                 "                        },\n" +
                 "                        \"RegPersonnelSourceSystem\": {\n" +
                 "                            \"@Code\": \"undchnundrtlpround\",\n" +
                 "                            \"SourceTimestamp\": \"2019-02-11T12:00:00\",\n" +
                 "                            \"AffiliateCode\": \"CHN\",\n" +
                 "                            \"MarketCode\": \"CHN\",\n" +
                 "                            \"DivisionCode\": \"01\",\n" +
                 "                            \"BrandCode\": \"01\",\n" +
                 "                            \"PersonnelCode\": \"23\"\n" +
                 "                        },\n" +
                 "                        \"NameGenderCode\": \"fml\",\n" +
                 "                        \"GenderCode\": \"fml\",\n" +
                 "                        \"CivilStatusCode\": \"fml\",\n" +
                 "                        \"ConsumerCountryCode_ISO3\": \"fml\",\n" +
                 "                        \"SpokenLanguageCode\": \"fml\",\n" +
                 "                        \"WrittenLanguageCode\": \"fml\",\n" +
                 "                        \"BirthDay\": 23,\n" +
                 "                        \"BirthMonth\": 1,\n" +
                 "                        \"BirthYear\": 2001,\n" +
                 "                        \"EstimatedBirthYear\": 2001,\n" +
                 "                        \"NameFilledFlag\": true,\n" +
                 "                        \"Salutation\": \"Ms\",\n" +
                 "                        \"LocalFirstName\": \"Carmen\",\n" +
                 "                        \"LocalMiddleName\": \"Ms\",\n" +
                 "                        \"LocalLastName\": \"Tu\",\n" +
                 "                        \"LocalFullName\": \"Ms\",\n" +
                 "                        \"LocalFirstName2\": \"Ms\",\n" +
                 "                        \"LocalMiddleName2\": \"Ms\",\n" +
                 "                        \"LocalLastName2\": \"Ms\",\n" +
                 "                        \"LocalFullName2\": \"Ms\",\n" +
                 "                        \"EnglishFirstName\": \"Ms\",\n" +
                 "                        \"EnglishMiddleName\": \"Ms\",\n" +
                 "                        \"EnglishLastName\": \"Ms\",\n" +
                 "                        \"EnglishFullName\": \"Ms\",\n" +
                 "                        \"IdentityNum\": \"101010101010\",\n" +
                 "                        \"PassportNum\": \"101010101010\",\n" +
                 "                        \"SocialSecurityNum\": \"101010101010\",\n" +
                 "                        \"Company\": \"Company 1\",\n" +
                 "                        \"Department\": \"Company 1\",\n" +
                 "                        \"JobTitle\": \"Company 1\",\n" +
                 "                        \"YearlyIncome\": \"190090.00\",\n" +
                 "                        \"SkinType\": {\n" +
                 "                            \"BrandCode\": \"01\",\n" +
                 "                            \"SkinTypeCode\": \"01\"\n" +
                 "                        },\n" +
                 "                        \"Currency\": {\n" +
                 "                            \"CurrencyCode\": \"USD\"\n" +
                 "                        },\n" +
                 "                        \"Ethnicity\": {\n" +
                 "                            \"MarketCode\": \"CHN\",\n" +
                 "                            \"EthnicityCode\": \"chn\"\n" +
                 "                        },\n" +
                 "                        \"ConsumerClassCode\": \"chn\",\n" +
                 "                        \"PreferredTouchPointCodeSourceSystem\": {\n" +
                 "                            \"SourceTimestamp\": \"2019-02-11T12:00:00\",\n" +
                 "                            \"AffiliateCode\": \"CHN\",\n" +
                 "                            \"MarketCode\": \"CHN\",\n" +
                 "                            \"DivisionCode\": \"CHN\",\n" +
                 "                            \"BrandCode\": \"CHN\",\n" +
                 "                            \"TouchPointCode\": \"CHN\"\n" +
                 "                        },\n" +
                 "                        \"LastVisitTouchPointCodeSourceSystem\": {\n" +
                 "                            \"SourceTimestamp\": \"2019-02-12T12:00:00\",\n" +
                 "                            \"MarketCode\": \"CHN\",\n" +
                 "                            \"BrandCode\": \"01\",\n" +
                 "                            \"TouchPointCode\": \"7\"\n" +
                 "                        },\n" +
                 "                        \"AssignedPersonnelSourceSystem\": {\n" +
                 "                            \"@Code\": \"undchnundrtlpround\",\n" +
                 "                            \"SourceTimestamp\": \"2019-02-11T12:00:00\",\n" +
                 "                            \"AffiliateCode\": \"CHN\",\n" +
                 "                            \"MarketCode\": \"CHN\",\n" +
                 "                            \"DivisionCode\": \"CHN\",\n" +
                 "                            \"BrandCode\": \"CHN\",\n" +
                 "                            \"PersonnelCode\": \"CHN\"\n" +
                 "                        },\n" +
                 "                        \"HobbyList\": {\n" +
                 "                            \"Hobby\": [\n" +
                 "                                {\n" +
                 "                                    \"HobbyDescription\": \"Cooking\"\n" +
                 "                                }\n" +
                 "                            ]\n" +
                 "                        },\n" +
                 "                        \"GeneralOptInFlag\": true,\n" +
                 "                        \"GeneralOptInDate\": \"2019-02-11T12:00:00\",\n" +
                 "                        \"DoNotContact\": true,\n" +
                 "                        \"SkinConcernsList\": {\n" +
                 "                            \"SkinConcerns\": [\n" +
                 "                                \"Dry Skin\"\n" +
                 "                            ]\n" +
                 "                        },\n" +
                 "                        \"HairTypeList\": {\n" +
                 "                            \"HairType\": [\n" +
                 "                                \"Curly\"\n" +
                 "                            ]\n" +
                 "                        },\n" +
                 "                        \"MakeUpConcernList\": {\n" +
                 "                            \"MakeUpConcerns\": [\n" +
                 "                                \"Dry Skin\"\n" +
                 "                            ]\n" +
                 "                        },\n" +
                 "                        \"AgeRange\": {\n" +
                 "                            \"AgeFrom\": \"30\",\n" +
                 "                            \"AgeTo\": \"30\"\n" +
                 "                        },\n" +
                 "                        \"HairConcernsList\": {\n" +
                 "                            \"HairConcerns\": [\n" +
                 "                                \"Dusty Hair\"\n" +
                 "                            ]\n" +
                 "                        },\n" +
                 "                        \"Nationality\": \"Chinese\",\n" +
                 "                        \"FirstPurchaseDate\": \"2019-02-11T12:00:00\"\n" +
                 "                    },\n" +
                 "                    \"ContactInformation\": {\n" +
                 "                        \"EMediaList\": {\n" +
                 "                            \"EMedia\": {\n" +
                 "                                \"@TypeCode\": \"emlprs\",\n" +
                 "                                \"SourceTimestamp\": \"2017-04-06T00:00:00Z\",\n" +
                 "                                \"Address\": \"carmen.tu88@yahoo.com\",\n" +
                 "                                \"DataQualityCode\": \"vld\",\n" +
                 "                                \"DataQualityDescription\": \"Valid\"\n" +
                 "                            }\n" +
                 "                        },\n" +
                 "                        \"PhoneList\": {\n" +
                 "                            \"Phone\": [\n" +
                 "                                {\n" +
                 "                                    \"@TypeCode\": \"mblprs\",\n" +
                 "                                    \"SourceTimestamp\": \"2019-02-11T12:00:00\",\n" +
                 "                                    \"CountryCode_ISO3\": \"CHN\",\n" +
                 "                                    \"PhoneCountryCode\": \"\",\n" +
                 "                                    \"RegionalCode\": \"\",\n" +
                 "                                    \"Extension\": \"\",\n" +
                 "                                    \"PhoneNumber\": \"51275127\",\n" +
                 "                                    \"RegPhoneTypeCode\": \"\",\n" +
                 "                                    \"Registrar\": \"\",\n" +
                 "                                    \"DataQualityCode\": \"vld\",\n" +
                 "                                    \"DataQualityDescription\": \"valid\"\n" +
                 "                                }\n" +
                 "                            ]\n" +
                 "                        },\n" +
                 "                        \"AddressList\": {\n" +
                 "                            \"Address\": {\n" +
                 "                                \"@TypeCode\": \"prs\",\n" +
                 "                                \"SourceTimestamp\": \"2017-04-06T00:00:00Z\",\n" +
                 "                                \"Address1\": \"8号室 李小方 （先生）收\",\n" +
                 "                                \"Address2\": \"8号室 李小方 （先生）收\",\n" +
                 "                                \"Address3\": \"8号室 李小方 （先生）收\",\n" +
                 "                                \"Address4\": \"8号室 李小方 （先生）收\",\n" +
                 "                                \"Address5\": \"8号室 李小方 （先生）收\",\n" +
                 "                                \"FlatNo\": \"as\",\n" +
                 "                                \"Floor\": \"as\",\n" +
                 "                                \"Block\": \"as\",\n" +
                 "                                \"Phase\": \"as\",\n" +
                 "                                \"Building\": \"as\",\n" +
                 "                                \"StreetNo\": \"as\",\n" +
                 "                                \"StreetNoSuffix\": \"as\",\n" +
                 "                                \"Alley\": \"as\",\n" +
                 "                                \"StreetName\": \"as\",\n" +
                 "                                \"StreetName2\": \"as\",\n" +
                 "                                \"Estate\": \"as\",\n" +
                 "                                \"LaneNo\": \"as\",\n" +
                 "                                \"LaneName\": \"as\",\n" +
                 "                                \"Sector\": \"as\",\n" +
                 "                                \"POBox\": \"as\",\n" +
                 "                                \"PostalCode\": \"4000\",\n" +
                 "                                \"SubCityCode\": \"as\",\n" +
                 "                                \"SubCityCodeDescription_en\": \"as\",\n" +
                 "                                \"SubCityCodeDescription_local\": \"as\",\n" +
                 "                                \"CityCode\": \"as\",\n" +
                 "                                \"CityDescription_en\": \"as\",\n" +
                 "                                \"CityDescription_local\": \"BRISBANE CITY\",\n" +
                 "                                \"Province\": \"as\",\n" +
                 "                                \"ProvinceCode\": \"as\",\n" +
                 "                                \"ProvinceDescription_en\": \"as\",\n" +
                 "                                \"ProvinceDescription_local\": \"QLD\",\n" +
                 "                                \"AdminArea\": \"as\",\n" +
                 "                                \"CountryCode_ISO3\": \"as\",\n" +
                 "                                \"NUTSCode\": \"as\",\n" +
                 "                                \"Longitude\": \"as\",\n" +
                 "                                \"Latitude\": \"as\",\n" +
                 "                                \"GeocodeResolution\": \"as\",\n" +
                 "                                \"GeographicalSpokenLanguage\": \"as\",\n" +
                 "                                \"DataQualityCode\": \"as\",\n" +
                 "                                \"DataQualityDescription\": \"as\"\n" +
                 "                            }\n" +
                 "                        }\n" +
                 "                    },\n" +
                 "                    \"OptInList\": {\n" +
                 "                        \"OptIn\": [\n" +
                 "                            {\n" +
                 "                                \"OptInTimestamp\": \"2017-04-06T00:00:00Z\",\n" +
                 "                                \"CommunicationChannelCode\": \"drcml\",\n" +
                 "                                \"OptInFlag\": true,\n" +
                 "                                \"OptInSourceSystemCode\": \"drcml\"\n" +
                 "                            }\n" +
                 "                        ]\n" +
                 "                    },\n" +
                 "                    \"CrossBrandOptInList\": {\n" +
                 "                        \"CrossBrandOptIn\": [\n" +
                 "                            {\n" +
                 "                                \"OptInTimestamp\": \"2019-02-11T12:00:00\",\n" +
                 "                                \"OptInFlag\": \"true\"\n" +
                 "                            }\n" +
                 "                        ]\n" +
                 "                    },\n" +
                 "                    \"AuxiliaryAttributeList\": {\n" +
                 "                        \"AuxiliaryAttribute\": [\n" +
                 "                            {\n" +
                 "                                \"@Code\": \"axlexm\",\n" +
                 "                                \"Description\": \"Auxilary Attributes example\",\n" +
                 "                                \"MultiValue\": \"false\",\n" +
                 "                                \"@Value\": \"example\",\n" +
                 "                                \"Active\": \"true\"\n" +
                 "                            }\n" +
                 "                        ]\n" +
                 "                    },\n" +
                 "                    \"TermsAndConditionList\": {\n" +
                 "                        \"TermsAndCondition\": [\n" +
                 "                            {\n" +
                 "                                \"@Code\": \"trmsexm\",\n" +
                 "                                \"Description\": \"Terms Example\",\n" +
                 "                                \"Version\": \"1.0\",\n" +
                 "                                \"AcceptedDate\": \"2019-02-11T12:00:00\"\n" +
                 "                            }\n" +
                 "                        ]\n" +
                 "                    },\n" +
                 "                    \"CustomAttributeList\": {\n" +
                 "                        \"CustomAttribute\": [\n" +
                 "                            {\n" +
                 "                                \"@Name\": \"Customer Id\",\n" +
                 "                                \"@Value\": \"1210\"\n" +
                 "                            }\n" +
                 "                        ]\n" +
                 "                    },\n" +
                 "                    \"CustomerGroupList\": {\n" +
                 "                        \"CustomerGroup\": [\n" +
                 "                            \"High Value\"\n" +
                 "                        ]\n" +
                 "                    },\n" +
                 "                    \"RemarkList\": {\n" +
                 "                        \"Remark\": [\n" +
                 "                            {\n" +
                 "                                \"RemarkCode\": \"tstrmk\",\n" +
                 "                                \"RemarksDate\": \"2019-02-11\",\n" +
                 "                                \"Remarks\": \"Test Remark\"\n" +
                 "                            }\n" +
                 "                        ]\n" +
                 "                    },\n" +
                 "                    \"NoteList\": {\n" +
                 "                        \"Note\": [\n" +
                 "                            {\n" +
                 "                                \"SeqNum\": \"1\",\n" +
                 "                                \"Type\": \"exm\",\n" +
                 "                                \"Location\": \"chn\",\n" +
                 "                                \"Note\": \"note\",\n" +
                 "                                \"CreateDate\": \"2019-02-11\",\n" +
                 "                                \"CreateBy\": \"BA\",\n" +
                 "                                \"UpdateDate\": \"2019-02-11\",\n" +
                 "                                \"UpdateBy\": \"BA\"\n" +
                 "                            }\n" +
                 "                        ]\n" +
                 "                    }\n" +
                 "                }\n" +
                 "            ]\n" +
                 "        }\n" +
                 "    }\n" +
                 "}";
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testFileutils() throws Exception {
        Map<String,String> map=new HashMap<>() ;
        map.put("name","String");
        map.put("age","Integer");
        map.put("user","User");
        FileUtils.writeToJava("zy.blue7.jsontoobj.utils","d:/a/a.java",map,null);
    }

    @Test
    void testParser() throws Exception {
        String str="{\n" +
                "    \"Header\": {\n" +
                "        \"@Action\": \"Create\",\n" +
                "        \"DocumentTimestamp\": \"2019-02-01T09:04:41\",\n" +
                "        \"DocumentUUID\": \"2e27d3a6-4288-472d-8742-b42f3d097954\"\n" +
                "    },\n" +
                "    \"ConsumerBestRecord\": {\n" +
                "        \"@RecordUUID\": \"c3999356-fb78-45f4-bc11-77dc3db7446b\",\n" +
                "        \"BestRecord\": {\n" +
                "            \"SourceSystemList\": {\n" +
                "                \"SourceSystem\": [\n" +
                "                    {\n" +
                "                        \"@Code\": \"iieieieieie\",\n" +
                "                        \"SourceTimestamp\": \"2019-02-13T12:00:00\",\n" +
                "                        \"AffiliateCode\": \"CHN\",\n" +
                "                        \"MarketCode\": \"CHN\",\n" +
                "                        \"DivisionCode\": \"EL\",\n" +
                "                        \"BrandCode\": \"EL\",\n" +
                "                        \"ConsumerId\": \"8383838\",\n" +
                "                        \"TerminaId\": \"7777\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        myJsonParser.parse(environment.getProperty("mainClassName"),str);
    }

    @Test
    void testStrUtils(){
        String str="sdasd";

        System.out.println(StringUtils.toUpperCaseFirstOne(str));
        str="GYGUG";
        System.out.println(StringUtils.toLowerCaseFirstOne(str));
    }

    @Test
    void testJsonParser() throws Exception {
//        String strJson="{\"Header\":{\"DocumentTimestamp\":\"2019-01-22T07:45:32\",\"DocumentUUID\":\"93853eca-16c0-4a30-b54d-3e30e9c19bcb\"},\"ConsumerBestRecord\":{\"RecordUUID\":\"dd52796b-8135-4a5b-8417-54c98bae4174\",\"BestRecord\":{\"SourceSystemList\":{\"SourceSystem\":[{\"Code\":\"undchnundrtlpround\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"AffiliateCode\":\"AUS\",\"MarketCode\":\"AUS\",\"DivisionCode\":\"01\",\"BrandCode\":\"1\",\"ConsumerId\":\"1004\"}]},\"Attributes\":{\"RecordTimestamp\":\"2019-01-22 07:10:32.0\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"CSRInstanceCode\":\"cnsmtlnd\",\"CSRInstanceDescription\":\"Consumer MDM Talend\",\"UniversalKey\":\"007bc773-f0fc-48bb-82b1-786c4dd59ce7\",\"RecognitionServiceCode\":\"cnsmtlndmdm\"},\"PersonalData\":{\"RegDate\":\"2019-01-07 00:00:00.0\",\"RegTouchPointSourceSystem\":{\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"MarketCode\":\"AUS\",\"BrandCode\":\"1\",\"TouchPointCode\":\"26\"},\"RegPersonnelSourceSystem\":{\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"AffiliateCode\":\"AUS\",\"MarketCode\":\"AUS\",\"DivisionCode\":\"01\",\"BrandCode\":\"1\",\"PersonnelCode\":\"43\"},\"NameGenderCode\":\"fml\",\"GenderCode\":\"fml\",\"ConsumerCountryCode_ISO3\":\"AUS\",\"SpokenLanguageCode\":\"eng\",\"WrittenLanguageCode\":\"englatn\",\"BirthDay\":14,\"BirthMonth\":1,\"BirthYear\":1981,\"EstimatedBirthYear\":1981,\"NameFilledFlag\":\"false\"},\"ProgramList\":null,\"CustomAttributeList\":null},\"DerivedBestRecordList\":[{\"SourceSystemList\":{\"SourceSystem\":[{\"Code\":\"undchnundrtlpround\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"AffiliateCode\":\"AUS\",\"MarketCode\":\"AUS\",\"DivisionCode\":\"01\",\"BrandCode\":\"1\"}]},\"Attributes\":{\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"MarketCode\":\"AUS\",\"BrandCode\":\"1\"},\"PersonalData\":{\"RegDate\":\"2019-01-07 00:00:00.0\",\"RegTouchPointSourceSystem\":{\"MarketCode\":\"AUS\",\"BrandCode\":\"1\",\"TouchPointCode\":\"26\"},\"RegPersonnelSourceSystem\":{\"Code\":\"undchnundrtlpround\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"AffiliateCode\":\"AUS\",\"MarketCode\":\"AUS\",\"DivisionCode\":\"01\",\"BrandCode\":\"1\",\"PersonnelCode\":\"43\"},\"GenderCode\":\"fml\",\"ConsumerCountryCode_ISO3\":\"AUS\",\"SpokenLanguageCode\":\"eng\",\"WrittenLanguageCode\":\"englatn\",\"BirthDay\":14,\"BirthMonth\":1,\"BirthYear\":1981,\"NameFilledFlag\":\"true\",\"Salutation\":\"Ms\",\"EnglishFirstName\":\"Sue\",\"EnglishLastName\":\"Puglia\",\"EnglishFullName\":\"Sue Puglia\",\"SkinType\":{\"BrandCode\":\"1\"},\"Ethnicity\":{\"MarketCode\":\"AUS\"},\"PreferredTouchPointCodeSourceSystem\":{\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"MarketCode\":\"AUS\",\"BrandCode\":\"1\",\"TouchPointCode\":\"26\"},\"LastVisitTouchPointCodeSourceSystem\":{\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"MarketCode\":\"AUS\",\"BrandCode\":\"1\"},\"AssignedPersonnelSourceSystem\":{\"Code\":\"undchnundrtlpround\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"MarketCode\":\"AUS\",\"AffiliateCode\":\"AUS\",\"DivisionCode\":\"01\",\"BrandCode\":\"1\",\"PersonnelCode\":\"43\"},\"HobbyList\":null,\"DoNotContact\":\"false\",\"SkinConcernsList\":null,\"HairTypeList\":null,\"MakeUpConcernList\":null,\"AgeRange\":null},\"ContactInformation\":{\"EMediaList\":{\"EMedia\":[{\"TypeCode\":\"emlprs\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\"}]},\"PhoneList\":{\"Phone\":[{\"TypeCode\":\"mblprs\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\"}]},\"AddressList\":null},\"OptInList\":null,\"CrossBrandOptInList\":null,\"AuxiliaryAttributeList\":null,\"TermsAndConditionList\":null,\"CustomAttributeList\":null,\"CustomerGroupList\":null,\"RemarkList\":null,\"NoteList\":null}]}}\n";

        myJsonParser.parse(environment.getProperty("mainClassName"),strJson);
    }

    @Test
    void testJsonParser1() throws Exception {
//        String strJson="{\"Header\":{\"DocumentTimestamp\":\"2019-01-22T07:45:32\",\"DocumentUUID\":\"93853eca-16c0-4a30-b54d-3e30e9c19bcb\"},\"ConsumerBestRecord\":{\"RecordUUID\":\"dd52796b-8135-4a5b-8417-54c98bae4174\",\"BestRecord\":{\"SourceSystemList\":{\"SourceSystem\":[{\"Code\":\"undchnundrtlpround\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"AffiliateCode\":\"AUS\",\"MarketCode\":\"AUS\",\"DivisionCode\":\"01\",\"BrandCode\":\"1\",\"ConsumerId\":\"1004\"}]},\"Attributes\":{\"RecordTimestamp\":\"2019-01-22 07:10:32.0\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"CSRInstanceCode\":\"cnsmtlnd\",\"CSRInstanceDescription\":\"Consumer MDM Talend\",\"UniversalKey\":\"007bc773-f0fc-48bb-82b1-786c4dd59ce7\",\"RecognitionServiceCode\":\"cnsmtlndmdm\"},\"PersonalData\":{\"RegDate\":\"2019-01-07 00:00:00.0\",\"RegTouchPointSourceSystem\":{\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"MarketCode\":\"AUS\",\"BrandCode\":\"1\",\"TouchPointCode\":\"26\"},\"RegPersonnelSourceSystem\":{\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"AffiliateCode\":\"AUS\",\"MarketCode\":\"AUS\",\"DivisionCode\":\"01\",\"BrandCode\":\"1\",\"PersonnelCode\":\"43\"},\"NameGenderCode\":\"fml\",\"GenderCode\":\"fml\",\"ConsumerCountryCode_ISO3\":\"AUS\",\"SpokenLanguageCode\":\"eng\",\"WrittenLanguageCode\":\"englatn\",\"BirthDay\":14,\"BirthMonth\":1,\"BirthYear\":1981,\"EstimatedBirthYear\":1981,\"NameFilledFlag\":\"false\"},\"ProgramList\":null,\"CustomAttributeList\":null},\"DerivedBestRecordList\":[{\"SourceSystemList\":{\"SourceSystem\":[{\"Code\":\"undchnundrtlpround\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"AffiliateCode\":\"AUS\",\"MarketCode\":\"AUS\",\"DivisionCode\":\"01\",\"BrandCode\":\"1\"}]},\"Attributes\":{\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"MarketCode\":\"AUS\",\"BrandCode\":\"1\"},\"PersonalData\":{\"RegDate\":\"2019-01-07 00:00:00.0\",\"RegTouchPointSourceSystem\":{\"MarketCode\":\"AUS\",\"BrandCode\":\"1\",\"TouchPointCode\":\"26\"},\"RegPersonnelSourceSystem\":{\"Code\":\"undchnundrtlpround\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"AffiliateCode\":\"AUS\",\"MarketCode\":\"AUS\",\"DivisionCode\":\"01\",\"BrandCode\":\"1\",\"PersonnelCode\":\"43\"},\"GenderCode\":\"fml\",\"ConsumerCountryCode_ISO3\":\"AUS\",\"SpokenLanguageCode\":\"eng\",\"WrittenLanguageCode\":\"englatn\",\"BirthDay\":14,\"BirthMonth\":1,\"BirthYear\":1981,\"NameFilledFlag\":\"true\",\"Salutation\":\"Ms\",\"EnglishFirstName\":\"Sue\",\"EnglishLastName\":\"Puglia\",\"EnglishFullName\":\"Sue Puglia\",\"SkinType\":{\"BrandCode\":\"1\"},\"Ethnicity\":{\"MarketCode\":\"AUS\"},\"PreferredTouchPointCodeSourceSystem\":{\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"MarketCode\":\"AUS\",\"BrandCode\":\"1\",\"TouchPointCode\":\"26\"},\"LastVisitTouchPointCodeSourceSystem\":{\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"MarketCode\":\"AUS\",\"BrandCode\":\"1\"},\"AssignedPersonnelSourceSystem\":{\"Code\":\"undchnundrtlpround\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\",\"MarketCode\":\"AUS\",\"AffiliateCode\":\"AUS\",\"DivisionCode\":\"01\",\"BrandCode\":\"1\",\"PersonnelCode\":\"43\"},\"HobbyList\":null,\"DoNotContact\":\"false\",\"SkinConcernsList\":null,\"HairTypeList\":null,\"MakeUpConcernList\":null,\"AgeRange\":null},\"ContactInformation\":{\"EMediaList\":{\"EMedia\":[{\"TypeCode\":\"emlprs\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\"}]},\"PhoneList\":{\"Phone\":[{\"TypeCode\":\"mblprs\",\"SourceTimestamp\":\"2019-01-07 17:25:21.0\"}]},\"AddressList\":null},\"OptInList\":null,\"CrossBrandOptInList\":null,\"AuxiliaryAttributeList\":null,\"TermsAndConditionList\":null,\"CustomAttributeList\":null,\"CustomerGroupList\":null,\"RemarkList\":null,\"NoteList\":null}]}}\n";

        myFastJsonParser.parse(environment.getProperty("mainClassName"),strJson);
    }
    @Test
    void testParseArr() throws Exception {
        myFastJsonParser.parse(environment.getProperty("mainClassName"),strParseArr);
    }

    @Test
    void testParseArrs() throws Exception {
        myFastJsonParser.parse(environment.getProperty("mainClassName"),strParseArrs);
    }

    @Test
    void testJsonType(){
        String str="\n" +
                "{\n" +
                "    \"intA\":12,\n" +
                "    \"strB\":\"dsdsd\",\n" +
                "    \"booleanC\":false,\n" +
                "    \"obj\":{\n" +
                "        \"a\":12\n" +
                "    },\n" +
                "    \"array\":[\n" +
                "        {},{}\n" +
                "    ],\n" +
                "    \"arra\":[\"1231\",\"2321\"],\n" +
                "    \"char\":\"d\",\n" +
                "    \n" +
                "    \"double\":12.32\n" +
                "\n" +
                "}";
        JSONObject obj= (JSONObject) JSON.parse(str);
        for(Map.Entry<String,Object> entry:obj.entrySet()){
            Object value = entry.getValue();
            if(value instanceof JSONObject){
                System.out.print("JSONObject : ");
            }
            else if(value instanceof JSONArray){
                System.out.print("JSONArray : ");
            }
            else if(value instanceof Number){
                System.out.print("Number : ");
            }
            else if(value instanceof String){
                System.out.print("String : ");
            }
            else if(value instanceof Boolean){
                System.out.print("Boolean : ");
            }
            else if(value instanceof BigDecimal){
                System.out.print("BigDecimal : ");
            }
            System.out.println(value.toString());
        }
    }
}
