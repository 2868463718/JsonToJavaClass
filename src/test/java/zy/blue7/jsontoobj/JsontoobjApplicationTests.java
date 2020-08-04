package zy.blue7.jsontoobj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import zy.blue7.jsontoobj.model.interfaces.IMyJsonParser;
import zy.blue7.jsontoobj.utils.FileUtils;
import zy.blue7.jsontoobj.utils.MyProperties;
import zy.blue7.jsontoobj.utils.StringUtils;

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

    @Test
    void contextLoads() {
    }

    @Test
    void testFileutils() throws Exception {
        Map<String,String> map=new HashMap<>() ;
        map.put("name","String");
        map.put("age","Integer");
        map.put("user","User");
        FileUtils.writeToJava("zy.blue7.jsontoobj.utils","d:/a/a.java",map);
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
            String strJson="{\n" +
                    "    \"Header\": {\n" +
                    "        \"@Action\": \"Create\",\n" +
                    "        \"DocumentTimestamp\": \"2019-02-01T09:04:41\",\n" +
                    "        \"DocumentUUID\": \"2e27d3a6-4288-472d-8742-b42f3d097954\"\n" +
                    "    },\n" +
                    "    \"ConsumerBestRecord\": {\n" +
                    "        \"@RecordUUID\": \"c3999356-fb78-45f4-bc11-77dc3db7446b\",\n" +
                    "        \"BestRecord\": {\n" +
                    "            \"SourceSystemList\": {\n" +
                    "                \"SourceSystem\": [   \n" +
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
                    "            },\n" +
                    "            \"Attributes\": {\n" +
                    "                \"RecordTimestamp\": \"2019-02-06T12:00:00\",\n" +
                    "                \"SourceTimestamp\": \"2019-02-14T12:00:00\",\n" +
                    "                \"CSRInstanceCode\": \"ueueue\",\n" +
                    "                \"CSRInstanceDescription\": \"test\",\n" +
                    "                \"UniversalKey\": \"012a73f2-202a-492e-acab-7914ebb93a97\",\n" +
                    "                \"RecognitionServiceCode\": \"777\"\n" +
                    "            },\n" +
                    "            \"PersonalData\": {\n" +
                    "                \"RegDate\": \"2019-02-06T12:00:00\",\n" +
                    "                \"RegTouchPointSourceSystem\": {\n" +
                    "                    \"SourceTimestamp\": \"2019-02-09T12:00:00\",\n" +
                    "                    \"MarketCode\": \"CHN\",\n" +
                    "                    \"BrandCode\": \"EL\",\n" +
                    "                    \"TouchPointCode\": \"1010\"\n" +
                    "                },\n" +
                    "                \"RegPersonnelSourceSystem\": {\n" +
                    "                    \"SourceTimestamp\": \"2019-02-05T12:00:00\",\n" +
                    "                    \"AffiliateCode\": \"EL\",\n" +
                    "                    \"MarketCode\": \"CHN\",\n" +
                    "                    \"DivisionCode\": \"EL\",\n" +
                    "                    \"BrandCode\": \"EL\",\n" +
                    "                    \"PersonnelCode\": \"1012\"\n" +
                    "                },\n" +
                    "                \"NameGenderCode\": \"fml\",\n" +
                    "                \"GenderCode\": \"fml\",\n" +
                    "                \"CivilStatusCode\": \"msrs\",\n" +
                    "                \"ConsumerCountryCode_ISO3\": \"CHN\",\n" +
                    "                \"SpokenLanguageCode\": \"MND\",\n" +
                    "                \"WrittenLanguageCode\": \"MND\",\n" +
                    "                \"BirthDay\": 8,\n" +
                    "                \"BirthMonth\": 8,\n" +
                    "                \"BirthYear\": 1988,\n" +
                    "                \"EstimatedBirthYear\": 1987,\n" +
                    "                \"NameFilledFlag\": \"true\"\n" +
                    "            },\n" +
                    "            \"ProgramList\": {\n" +
                    "                \"Program\": [\n" +
                    "                    {\n" +
                    "                        \"ApplicationTouchPointCode\": \"2020\",\n" +
                    "                        \"ConsumerGroup\": \"Consumer\",\n" +
                    "                        \"ProgramTypeCode\": \"award\",\n" +
                    "                        \"ProgramTypeDescription\": \"awards china\",\n" +
                    "                        \"ProgramLevelCode\": \"7\",\n" +
                    "                        \"ProgramLevelDescription\": \"king\",\n" +
                    "                        \"ProgramSystemIDCode\": \"5555\",\n" +
                    "                        \"ProgramSystemIDDescription\": \"highest award\",\n" +
                    "                        \"MembershipNum\": \"66366636636\",\n" +
                    "                        \"CardNum\": \"8848488488484\",\n" +
                    "                        \"StartTimestamp\": \"2019-02-11T12:00:00\",\n" +
                    "                        \"PointsAcquired\": \"150\",\n" +
                    "                        \"PointsRedeemed\": \"50\",\n" +
                    "                        \"InitialQuota\": \"150\",\n" +
                    "                        \"AvailableQuota\": \"100\"\n" +
                    "                    }\n" +
                    "                ]\n" +
                    "            },\n" +
                    "            \"CustomAttributeList\": {\n" +
                    "                \"CustomAttribute\": [\n" +
                    "                    {\n" +
                    "                        \"@Name\": \"ConsumerID\",\n" +
                    "                        \"@Value\": \"12\"\n" +
                    "                    }\n" +
                    "                ]\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"DerivedBestRecordList\": [\n" +
                    "            {\n" +
                    "                \"@Level\": \"2\",\n" +
                    "                \"SourceSystemList\": {\n" +
                    "                    \"SourceSystem\": [\n" +
                    "                        {\n" +
                    "                            \"@Code\": \"74747474\",\n" +
                    "                            \"SourceTimestamp\": \"2019-02-05T12:00:00\",\n" +
                    "                            \"AffiliateCode\": \"EL\",\n" +
                    "                            \"MarketCode\": \"CHN\",\n" +
                    "                            \"DivisionCode\": \"EL\",\n" +
                    "                            \"BrandCode\": \"EL\",\n" +
                    "                            \"ConsumerId\": \"yeyeyeyeyey\",\n" +
                    "                            \"TerminaId\": \"12\"\n" +
                    "                        }\n" +
                    "                    ]\n" +
                    "                },\n" +
                    "                \"Attributes\": {\n" +
                    "                    \"RecordTimestamp\": \"2019-02-05T12:00:00\",\n" +
                    "                    \"SourceTimestamp\": \"2019-02-09T12:00:00\",\n" +
                    "                    \"MarketCode\": \"CHN\",\n" +
                    "                    \"BrandCode\": \"EL\",\n" +
                    "                    \"RetailerHierarchyCode\": \"MCY\",\n" +
                    "                    \"TouchPointCode\": \"1212\"\n" +
                    "                },\n" +
                    "                \"PersonalData\": {\n" +
                    "                    \"RegDate\": \"2019-02-05T12:00:00\",\n" +
                    "                    \"RegTouchPointSourceSystem\": {\n" +
                    "                        \"SourceTimestamp\": \"2019-02-13T12:00:00\",\n" +
                    "                        \"MarketCode\": \"CHN\",\n" +
                    "                        \"BrandCode\": \"EL\",\n" +
                    "                        \"TouchPointCode\": \"1212\"\n" +
                    "                    },\n" +
                    "                    \"RegPersonnelSourceSystem\": {\n" +
                    "                        \"@Code\": \"ueueueu\",\n" +
                    "                        \"SourceTimestamp\": \"2019-02-13T12:00:00\",\n" +
                    "                        \"AffiliateCode\": \"LAM\",\n" +
                    "                        \"MarketCode\": \"CHN\",\n" +
                    "                        \"DivisionCode\": \"EL\",\n" +
                    "                        \"BrandCode\": \"EL\",\n" +
                    "                        \"PersonnelCode\": \"1010\"\n" +
                    "                    },\n" +
                    "                    \"NameGenderCode\": \"fml\",\n" +
                    "                    \"GenderCode\": \"fml\",\n" +
                    "                    \"CivilStatusCode\": \"msrss\",\n" +
                    "                    \"ConsumerCountryCode_ISO3\": \"CHN\",\n" +
                    "                    \"SpokenLanguageCode\": \"MND\",\n" +
                    "                    \"WrittenLanguageCode\": \"MND\",\n" +
                    "                    \"BirthDay\": 8,\n" +
                    "                    \"BirthMonth\": 8,\n" +
                    "                    \"BirthYear\": 1988,\n" +
                    "                    \"EstimatedBirthYear\": 1997,\n" +
                    "                    \"NameFilledFlag\": \"true\",\n" +
                    "                    \"Salutation\": \"Mr\",\n" +
                    "                    \"EnglishFirstName\": \"tony\",\n" +
                    "                    \"EnglishMiddleName\": \"w\",\n" +
                    "                    \"EnglishLastName\": \"Pearson\",\n" +
                    "                    \"EnglishFullName\": \"Tony W Pearson\",\n" +
                    "                    \"IdentityNum\": \"84848484\",\n" +
                    "                    \"PassportNum\": \"7474747239\",\n" +
                    "                    \"SocialSecurityNum\": \"8888888888888888888\",\n" +
                    "                    \"Company\": \"artha\",\n" +
                    "                    \"Department\": \"software\",\n" +
                    "                    \"JobTitle\": \"architect\",\n" +
                    "                    \"YearlyIncome\": \"10.00\",\n" +
                    "                    \"SkinType\": {\n" +
                    "                        \"BrandCode\": \"el\",\n" +
                    "                        \"SkinTypeCode\": \"dry\"\n" +
                    "                    },\n" +
                    "                    \"CurrencyCode\": \"USD\",\n" +
                    "                    \"Ethnicity\": {\n" +
                    "                        \"MarketCode\": \"CHN\",\n" +
                    "                        \"EthnicityCode\": \"CAU\"\n" +
                    "                    },\n" +
                    "                    \"PreferredTouchPointCodeSourceSystem\": {\n" +
                    "                        \"SourceTimestamp\": \"2019-02-06T12:00:00\",\n" +
                    "                        \"MarketCode\": \"CHN\",\n" +
                    "                        \"BrandCode\": \"EL\",\n" +
                    "                        \"TouchPointCode\": \"1010\"\n" +
                    "                    },\n" +
                    "                    \"LastVisitTouchPointCodeSourceSystem\": {\n" +
                    "                        \"SourceTimestamp\": \"2019-02-05T12:00:00\",\n" +
                    "                        \"MarketCode\": \"CHN\",\n" +
                    "                        \"BrandCode\": \"EL\",\n" +
                    "                        \"TouchPointCode\": \"1212\"\n" +
                    "                    },\n" +
                    "                    \"AssignedPersonnelSourceSystem\": {\n" +
                    "                        \"@Code\": \"ueueueueu\",\n" +
                    "                        \"SourceTimestamp\": \"2019-02-13T12:00:00\",\n" +
                    "                        \"MarketCode\": \"EL\",\n" +
                    "                        \"AffiliateCode\": \"EL\",\n" +
                    "                        \"DivisionCode\": \"EL\",\n" +
                    "                        \"BrandCode\": \"EL\",\n" +
                    "                        \"PersonnelCode\": \"1234\"\n" +
                    "                    },\n" +
                    "                    \"HobbyList\": {\n" +
                    "                        \"Hobby\": [\n" +
                    "                            {\n" +
                    "                                \"HobbyDescription\": \"Skiing\"\n" +
                    "                            }\n" +
                    "                        ]\n" +
                    "                    },\n" +
                    "                    \"GeneralOptInFlag\": \"true\",\n" +
                    "                    \"GeneralOptInDate\": \"2019-02-05T12:00:00\",\n" +
                    "                    \"DoNotContact\": \"false\",\n" +
                    "                    \"SkinConcernsList\": {\n" +
                    "                        \"SkinConcerns\": [\n" +
                    "                            \"oily\"\n" +
                    "                        ]\n" +
                    "                    },\n" +
                    "                    \"HairTypeList\": {\n" +
                    "                        \"HairType\": [\n" +
                    "                            \"wavy\"\n" +
                    "                        ]\n" +
                    "                    },\n" +
                    "                    \"MakeUpConcernList\": {\n" +
                    "                        \"MakeUpConcerns\": [\n" +
                    "                            \"acne\"\n" +
                    "                        ]\n" +
                    "                    },\n" +
                    "                    \"AgeRange\": {\n" +
                    "                        \"AgeFrom\": \"33\",\n" +
                    "                        \"AgeTo\": \"35\"\n" +
                    "                    },\n" +
                    "                    \"HairConcernsList\": {\n" +
                    "                        \"HairConcerns\": [\n" +
                    "                            \"split ends\"\n" +
                    "                        ]\n" +
                    "                    },\n" +
                    "                    \"Nationality\": \"US\"\n" +
                    "                },\n" +
                    "                \"ContactInformation\": {\n" +
                    "                    \"EMediaList\": {\n" +
                    "                        \"EMedia\": [\n" +
                    "                            {\n" +
                    "                                \"@TypeCode\": \"emlprs\",\n" +
                    "                                \"SourceTimestamp\": \"2019-02-04T12:00:00\",\n" +
                    "                                \"Address\": \"tony@thinkartha.com\",\n" +
                    "                                \"DataQualityCode\": \"vld\",\n" +
                    "                                \"DataQualityDescription\": \"valid\"\n" +
                    "                            }\n" +
                    "                        ]\n" +
                    "                    },\n" +
                    "                    \"PhoneList\": {\n" +
                    "                        \"Phone\": [\n" +
                    "                            {\n" +
                    "                                \"@TypeCode\": \"phprs\",\n" +
                    "                                \"SourceTimestamp\": \"2019-02-04T12:00:00\",\n" +
                    "                                \"CountryCode_ISO3\": \"USA\",\n" +
                    "                                \"PhoneNumber\": \"777-888-9977\",\n" +
                    "                                \"DataQualityCode\": \"vld\",\n" +
                    "                                \"DataQualityDescription\": \"valid\"\n" +
                    "                            }\n" +
                    "                        ]\n" +
                    "                    },\n" +
                    "                    \"AddressList\": {\n" +
                    "                        \"Address\": [\n" +
                    "                            {\n" +
                    "                                \"@TypeCode\": \"prs\",\n" +
                    "                                \"SourceTimestamp\": \"2019-02-11T12:00:00\",\n" +
                    "                                \"Address1\": \"1234 kaymin\",\n" +
                    "                                \"CityCode\": \"HEND\",\n" +
                    "                                \"CountryCode_ISO3\": \"USA\",\n" +
                    "                                \"DataQualityCode\": \"vld\",\n" +
                    "                                \"DataQualityDescription\": \"valid\"\n" +
                    "                            }\n" +
                    "                        ]\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                \"OptInList\": {\n" +
                    "                    \"OptIn\": [\n" +
                    "                        {\n" +
                    "                            \"OptInTimestamp\": \"2019-02-05T12:00:00\",\n" +
                    "                            \"CommunicationChannelCode\": \"email\",\n" +
                    "                            \"OptInFlag\": \"true\",\n" +
                    "                            \"OptInSourceSystemCode\": \"123\"\n" +
                    "                        }\n" +
                    "                    ]\n" +
                    "                },\n" +
                    "                \"CrossBrandOptInList\": {\n" +
                    "                    \"CrossBrandOptIn\": [\n" +
                    "                        {\n" +
                    "                            \"OptInTimestamp\": \"2019-01-31T12:00:00\",\n" +
                    "                            \"OptInFlag\": \"true\"\n" +
                    "                        }\n" +
                    "                    ]\n" +
                    "                },\n" +
                    "                \"AuxiliaryAttributeList\": {\n" +
                    "                    \"AuxiliaryAttribute\": [\n" +
                    "                        {\n" +
                    "                            \"@Code\": \"63636\",\n" +
                    "                            \"Description\": \"test\",\n" +
                    "                            \"@Value\": \"7\",\n" +
                    "                            \"Active\": \"true\"\n" +
                    "                        }\n" +
                    "                    ]\n" +
                    "                },\n" +
                    "                \"TermsAndConditionList\": {\n" +
                    "                    \"TermsAndCondition\": [\n" +
                    "                        {\n" +
                    "                            \"@Code\": \"act\",\n" +
                    "                            \"Description\": \"accept\",\n" +
                    "                            \"Version\": \"3\",\n" +
                    "                            \"AcceptedDate\": \"2019-02-13T12:00:00\"\n" +
                    "                        }\n" +
                    "                    ]\n" +
                    "                },\n" +
                    "                \"CustomAttributeList\": {\n" +
                    "                    \"CustomAttribute\": [\n" +
                    "                        {\n" +
                    "                            \"@Name\": \"nickname\",\n" +
                    "                            \"@Value\": \"tiger\"\n" +
                    "                        }\n" +
                    "                    ]\n" +
                    "                },\n" +
                    "                \"CustomerGroupList\": {\n" +
                    "                    \"CustomerGroup\": [\n" +
                    "                        \"235\"\n" +
                    "                    ]\n" +
                    "                },\n" +
                    "                \"RemarkList\": {\n" +
                    "                    \"Remark\": [\n" +
                    "                        {\n" +
                    "                            \"RemarkCode\": \"rtrt\",\n" +
                    "                            \"RemarksDate\": \"2019-01-29\",\n" +
                    "                            \"Remarks\": \"test now\"\n" +
                    "                        }\n" +
                    "                    ]\n" +
                    "                },\n" +
                    "                \"NoteList\": {\n" +
                    "                    \"Note\": [\n" +
                    "                        {\n" +
                    "                            \"SeqNum\": \"1\",\n" +
                    "                            \"Type\": \"gh\",\n" +
                    "                            \"Location\": \"MACAU\",\n" +
                    "                            \"Note\": \"hello\",\n" +
                    "                            \"CreateDate\": \"2019-02-16T00:00:00\",\n" +
                    "                            \"CreateBy\": \"ap\",\n" +
                    "                            \"UpdateDate\": \"2019-01-29T00:00:00\",\n" +
                    "                            \"UpdateBy\": \"tony\"\n" +
                    "                        }\n" +
                    "                    ]\n" +
                    "                }\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}";
        myJsonParser.parse(environment.getProperty("mainClassName"),strJson);
    }
}
