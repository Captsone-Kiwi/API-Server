package com.kiwi.ApiServer.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HanKeyToEngKey {

    /** 모음 */
    private final int VOWEL = 21;
    /** 표음문자 */
    private final int PHONOGRAM = 28;

    /** 초성 키 맵 */
    private final Map<Integer, String> initialConsonantList = new HashMap<Integer, String>();
    /** 중성 키 맵 */
    private final Map<Integer, String> syllableNucleusList = new HashMap<Integer, String>();
    /** 종성 키 맵 */
    private final Map<Integer, String> finalConsonantList = new HashMap<Integer, String>();
    /** 단일 글자 키 맵 */
    private final Map<Integer, String> compatibilityList = new HashMap<Integer, String>();

    /** 생성 인스턴스 */
    public static HanKeyToEngKey mInstance = null;

    /**
     * 인스턴스 얻기
     * @return
     */
    public static HanKeyToEngKey getInstance(){
        if( mInstance == null )
            mInstance = new HanKeyToEngKey();

        return mInstance;
    }

    /**
     * Creator
     */
    public HanKeyToEngKey(){
        this.InitList();
    }

    /**
     * 키 생성
     */
    public void InitList(){
        // 초성
        initialConsonantList.put(0x1100, "r");
        initialConsonantList.put(0x1101, "R");
        initialConsonantList.put(0x1102, "s");
        initialConsonantList.put(0x1103, "e");
        initialConsonantList.put(0x1104, "E");
        initialConsonantList.put(0x1105, "f");
        initialConsonantList.put(0x1106, "a");
        initialConsonantList.put(0x1107, "q");
        initialConsonantList.put(0x1108, "Q");
        initialConsonantList.put(0x1109, "t");
        initialConsonantList.put(0x110a, "T");
        initialConsonantList.put(0x110b, "d");
        initialConsonantList.put(0x110c, "w");
        initialConsonantList.put(0x110d, "W");
        initialConsonantList.put(0x110e, "c");
        initialConsonantList.put(0x110f, "z");
        initialConsonantList.put(0x1110, "x");
        initialConsonantList.put(0x1111, "v");
        initialConsonantList.put(0x1112, "g");
        initialConsonantList.put(0x1113, "sr");
        initialConsonantList.put(0x1114, "ss");
        initialConsonantList.put(0x1115, "se");
        initialConsonantList.put(0x1116, "sq");
        initialConsonantList.put(0x1117, "er");
        initialConsonantList.put(0x1118, "fs");
        initialConsonantList.put(0x1119, "ff");
        initialConsonantList.put(0x111a, "fg");
//		initialConsonantList.put(0x111b, "");
        initialConsonantList.put(0x111c, "aq");
//		initialConsonantList.put(0x111d, "");
        initialConsonantList.put(0x111e, "qr");
        initialConsonantList.put(0x111f, "qs");
        initialConsonantList.put(0x1120, "qe");
        initialConsonantList.put(0x1121, "qt");
        initialConsonantList.put(0x1122, "qtr");
        initialConsonantList.put(0x1123, "qte");
        initialConsonantList.put(0x1124, "qtq");
        initialConsonantList.put(0x1125, "qtt");
        initialConsonantList.put(0x1126, "qtw");
        initialConsonantList.put(0x1127, "qw");
        initialConsonantList.put(0x1128, "qc");
        initialConsonantList.put(0x1129, "qx");
        initialConsonantList.put(0x112a, "qv");
//		initialConsonantList.put(0x112b, "");
//		initialConsonantList.put(0x112c, "");
        initialConsonantList.put(0x112d, "tr");
        initialConsonantList.put(0x112e, "ts");
        initialConsonantList.put(0x112f, "te");
        initialConsonantList.put(0x1130, "tf");
        initialConsonantList.put(0x1131, "ta");
        initialConsonantList.put(0x1132, "tq");
        initialConsonantList.put(0x1133, "tqr");
        initialConsonantList.put(0x1134, "ttt");
        initialConsonantList.put(0x1135, "td");
        initialConsonantList.put(0x1136, "tw");
        initialConsonantList.put(0x1137, "tc");
        initialConsonantList.put(0x1138, "tz");
        initialConsonantList.put(0x1139, "tx");
        initialConsonantList.put(0x113a, "tv");
        initialConsonantList.put(0x113b, "tg");
        initialConsonantList.put(0x113c, "t");
        initialConsonantList.put(0x113d, "T");
        initialConsonantList.put(0x113e, "t");
        initialConsonantList.put(0x113f, "T");
//		initialConsonantList.put(0x1140, "");
        initialConsonantList.put(0x1141, "dr");
        initialConsonantList.put(0x1142, "de");
        initialConsonantList.put(0x1143, "da");
        initialConsonantList.put(0x1144, "dq");
        initialConsonantList.put(0x1145, "dt");
//		initialConsonantList.put(0x1146, "");
        initialConsonantList.put(0x1147, "dd");
        initialConsonantList.put(0x1148, "dw");
        initialConsonantList.put(0x1149, "dc");
        initialConsonantList.put(0x114a, "dx");
        initialConsonantList.put(0x114b, "dv");
        initialConsonantList.put(0x114c, "d");
        initialConsonantList.put(0x114d, "wd");
        initialConsonantList.put(0x114e, "w");
        initialConsonantList.put(0x114f, "W");
        initialConsonantList.put(0x1150, "w");
        initialConsonantList.put(0x1151, "W");
        initialConsonantList.put(0x1152, "cz");
        initialConsonantList.put(0x1153, "cg");
        initialConsonantList.put(0x1154, "c");
        initialConsonantList.put(0x1155, "vq");
        initialConsonantList.put(0x1156, "vg");
//		initialConsonantList.put(0x1157, "");
        initialConsonantList.put(0x1158, "gg");
//		initialConsonantList.put(0x1159, "");
        initialConsonantList.put(0x115a, "re");
        initialConsonantList.put(0x115b, "st");
        initialConsonantList.put(0x115c, "sw");
        initialConsonantList.put(0x115d, "sg");
        initialConsonantList.put(0x115e, "ef");
//		initialConsonantList.put(0x115f, "");

        // 중성
//		syllableNucleusList.put(0x1160, "");
        syllableNucleusList.put(0x1161, "k");
        syllableNucleusList.put(0x1162, "o");
        syllableNucleusList.put(0x1163, "i");
        syllableNucleusList.put(0x1164, "O");
        syllableNucleusList.put(0x1165, "j");
        syllableNucleusList.put(0x1166, "p");
        syllableNucleusList.put(0x1167, "u");
        syllableNucleusList.put(0x1168, "P");
        syllableNucleusList.put(0x1169, "h");
        syllableNucleusList.put(0x116a, "hk");
        syllableNucleusList.put(0x116b, "ho");
        syllableNucleusList.put(0x116c, "hl");
        syllableNucleusList.put(0x116d, "y");
        syllableNucleusList.put(0x116e, "n");
        syllableNucleusList.put(0x116f, "nj");
        syllableNucleusList.put(0x1170, "np");
        syllableNucleusList.put(0x1171, "nl");
        syllableNucleusList.put(0x1172, "b");
        syllableNucleusList.put(0x1173, "m");
        syllableNucleusList.put(0x1174, "ml");
        syllableNucleusList.put(0x1175, "l");
        syllableNucleusList.put(0x1176, "hk");
        syllableNucleusList.put(0x1177, "nk");
        syllableNucleusList.put(0x1178, "hi");
        syllableNucleusList.put(0x1179, "yi");
        syllableNucleusList.put(0x117a, "hj");
        syllableNucleusList.put(0x117b, "nj");
        syllableNucleusList.put(0x117c, "mj");
        syllableNucleusList.put(0x117d, "hu");
        syllableNucleusList.put(0x117e, "nu");
        syllableNucleusList.put(0x117f, "hj");
        syllableNucleusList.put(0x1180, "nP");
        syllableNucleusList.put(0x1181, "hP");
        syllableNucleusList.put(0x1182, "hh");
        syllableNucleusList.put(0x1183, "hn");
        syllableNucleusList.put(0x1184, "yi");
        syllableNucleusList.put(0x1185, "yO");
        syllableNucleusList.put(0x1186, "yu");
        syllableNucleusList.put(0x1187, "yh");
        syllableNucleusList.put(0x1188, "yl");
        syllableNucleusList.put(0x1189, "nk");
        syllableNucleusList.put(0x118a, "no");
        syllableNucleusList.put(0x118b, "njm");
        syllableNucleusList.put(0x118c, "nP");
        syllableNucleusList.put(0x118d, "nn");
        syllableNucleusList.put(0x118e, "bk");
        syllableNucleusList.put(0x118f, "bj");
        syllableNucleusList.put(0x1190, "bp");
        syllableNucleusList.put(0x1191, "bu");
        syllableNucleusList.put(0x1192, "bP");
        syllableNucleusList.put(0x1193, "bn");
        syllableNucleusList.put(0x1194, "bl");
        syllableNucleusList.put(0x1195, "mn");
        syllableNucleusList.put(0x1196, "mm");
        syllableNucleusList.put(0x1197, "mln");
        syllableNucleusList.put(0x1198, "lk");
        syllableNucleusList.put(0x1199, "li");
        syllableNucleusList.put(0x119a, "hl");
        syllableNucleusList.put(0x119b, "nl");
        syllableNucleusList.put(0x119c, "ml");
//		syllableNucleusList.put(0x119d, "");
//		syllableNucleusList.put(0x119e, "");
//		syllableNucleusList.put(0x119f, "");
//		syllableNucleusList.put(0x11a0, "");
//		syllableNucleusList.put(0x11a1, "");
//		syllableNucleusList.put(0x11a2, "");
        syllableNucleusList.put(0x11a3, "mk");
        syllableNucleusList.put(0x11a4, "ni");
        syllableNucleusList.put(0x11a5, "ui");
        syllableNucleusList.put(0x11a6, "hi");
        syllableNucleusList.put(0x11a7, "hO");

        // 종성
        finalConsonantList.put(0x11a8, "r");
        finalConsonantList.put(0x11a9, "R");
        finalConsonantList.put(0x11aa, "rt");
        finalConsonantList.put(0x11ab, "s");
        finalConsonantList.put(0x11ac, "sw");
        finalConsonantList.put(0x11ad, "sg");
        finalConsonantList.put(0x11ae, "e");
        finalConsonantList.put(0x11af, "f");
        finalConsonantList.put(0x11b0, "fr");
        finalConsonantList.put(0x11b1, "fa");
        finalConsonantList.put(0x11b2, "fq");
        finalConsonantList.put(0x11b3, "ft");
        finalConsonantList.put(0x11b4, "fx");
        finalConsonantList.put(0x11b5, "fv");
        finalConsonantList.put(0x11b6, "fg");
        finalConsonantList.put(0x11b7, "a");
        finalConsonantList.put(0x11b8, "q");
        finalConsonantList.put(0x11b9, "qt");
        finalConsonantList.put(0x11ba, "t");
        finalConsonantList.put(0x11bb, "T");
        finalConsonantList.put(0x11bc, "d");
        finalConsonantList.put(0x11bd, "w");
        finalConsonantList.put(0x11be, "c");
        finalConsonantList.put(0x11bf, "z");
        finalConsonantList.put(0x11c0, "x");
        finalConsonantList.put(0x11c1, "v");
        finalConsonantList.put(0x11c2, "g");
        finalConsonantList.put(0x11c3, "rf");
        finalConsonantList.put(0x11c4, "rtr");
        finalConsonantList.put(0x11c5, "sr");
        finalConsonantList.put(0x11c6, "se");
        finalConsonantList.put(0x11c7, "st");
//		finalConsonantList.put(0x11c8, "");
        finalConsonantList.put(0x11c9, "sx");
        finalConsonantList.put(0x11ca, "er");
        finalConsonantList.put(0x11cb, "ef");
        finalConsonantList.put(0x11cc, "frt");
        finalConsonantList.put(0x11cd, "fs");
        finalConsonantList.put(0x11ce, "fe");
        finalConsonantList.put(0x11cf, "feg");
        finalConsonantList.put(0x11d0, "ff");
        finalConsonantList.put(0x11d1, "far");
        finalConsonantList.put(0x11d2, "fat");
        finalConsonantList.put(0x11d3, "fqt");
        finalConsonantList.put(0x11d4, "fqg");
//		finalConsonantList.put(0x11d5, "");
        finalConsonantList.put(0x11d6, "fT");
//		finalConsonantList.put(0x11d7, "");
        finalConsonantList.put(0x11d8, "fz");
//		finalConsonantList.put(0x11d9, "");
        finalConsonantList.put(0x11da, "ar");
        finalConsonantList.put(0x11db, "af");
        finalConsonantList.put(0x11dc, "aq");
        finalConsonantList.put(0x11dd, "at");
        finalConsonantList.put(0x11de, "aT");
//		finalConsonantList.put(0x11df, "");
        finalConsonantList.put(0x11e0, "ac");
        finalConsonantList.put(0x11e1, "ag");
//		finalConsonantList.put(0x11e2, "");
        finalConsonantList.put(0x11e3, "qf");
        finalConsonantList.put(0x11e4, "qv");
        finalConsonantList.put(0x11e5, "qg");
//		finalConsonantList.put(0x11e6, "");
        finalConsonantList.put(0x11e7, "tr");
        finalConsonantList.put(0x11e8, "te");
        finalConsonantList.put(0x11e9, "tf");
        finalConsonantList.put(0x11ea, "tq");
//		finalConsonantList.put(0x11eb, "");
//		finalConsonantList.put(0x11ec, "");
//		finalConsonantList.put(0x11ed, "");
//		finalConsonantList.put(0x11ee, "");
//		finalConsonantList.put(0x11ef, "");
//		finalConsonantList.put(0x11f0, "");
//		finalConsonantList.put(0x11f1, "");
//		finalConsonantList.put(0x11f2, "");
        finalConsonantList.put(0x11f3, "vq");
//		finalConsonantList.put(0x11f4, "");
        finalConsonantList.put(0x11f5, "gs");
        finalConsonantList.put(0x11f6, "gf");
        finalConsonantList.put(0x11f7, "ga");
        finalConsonantList.put(0x11f8, "gq");
//		finalConsonantList.put(0x11f9, "");
        finalConsonantList.put(0x11fa, "rs");
        finalConsonantList.put(0x11fb, "rq");
        finalConsonantList.put(0x11fc, "rc");
        finalConsonantList.put(0x11fd, "rz");
        finalConsonantList.put(0x11fe, "rg");
        finalConsonantList.put(0x11ff, "ss");

        // 자음만
//		compatibilityList.put(0x3130, "");
        compatibilityList.put(0x3131, "r");
        compatibilityList.put(0x3132, "R");
        compatibilityList.put(0x3133, "rt");
        compatibilityList.put(0x3134, "s");
        compatibilityList.put(0x3135, "sw");
        compatibilityList.put(0x3136, "sg");
        compatibilityList.put(0x3137, "e");
        compatibilityList.put(0x3138, "E");
        compatibilityList.put(0x3139, "f");
        compatibilityList.put(0x313a, "fr");
        compatibilityList.put(0x313b, "fa");
        compatibilityList.put(0x313c, "fq");
        compatibilityList.put(0x313d, "ft");
        compatibilityList.put(0x313e, "fx");
        compatibilityList.put(0x313f, "fv");
        compatibilityList.put(0x3140, "fg");
        compatibilityList.put(0x3141, "a");
        compatibilityList.put(0x3142, "q");
        compatibilityList.put(0x3143, "Q");
        compatibilityList.put(0x3144, "qt");
        compatibilityList.put(0x3145, "t");
        compatibilityList.put(0x3146, "T");
        compatibilityList.put(0x3147, "d");
        compatibilityList.put(0x3148, "w");
        compatibilityList.put(0x3149, "W");
        compatibilityList.put(0x314a, "c");
        compatibilityList.put(0x314b, "z");
        compatibilityList.put(0x314c, "x");
        compatibilityList.put(0x314d, "v");
        compatibilityList.put(0x314e, "g");
        compatibilityList.put(0x314f, "k");
        compatibilityList.put(0x3150, "o");
        compatibilityList.put(0x3151, "i");
        compatibilityList.put(0x3152, "O");
        compatibilityList.put(0x3153, "j");
        compatibilityList.put(0x3154, "p");
        compatibilityList.put(0x3155, "u");
        compatibilityList.put(0x3156, "P");
        compatibilityList.put(0x3157, "h");
        compatibilityList.put(0x3158, "hk");
        compatibilityList.put(0x3159, "ho");
        compatibilityList.put(0x315a, "hl");
        compatibilityList.put(0x315b, "y");
        compatibilityList.put(0x315c, "n");
        compatibilityList.put(0x315d, "nj");
        compatibilityList.put(0x315e, "np");
        compatibilityList.put(0x315f, "nl");
        compatibilityList.put(0x3160, "b");
        compatibilityList.put(0x3161, "m");
        compatibilityList.put(0x3162, "ml");
        compatibilityList.put(0x3163, "l");
//		compatibilityList.put(0x3164, "");
        compatibilityList.put(0x3165, "ss");
        compatibilityList.put(0x3166, "se");
        compatibilityList.put(0x3167, "st");
//		compatibilityList.put(0x3168, "");
        compatibilityList.put(0x3169, "frt");
        compatibilityList.put(0x316a, "fe");
        compatibilityList.put(0x316b, "fqt");
//		compatibilityList.put(0x316c, "");
//		compatibilityList.put(0x316d, "");
        compatibilityList.put(0x316e, "aq");
        compatibilityList.put(0x316f, "at");
//		compatibilityList.put(0x3170, "");
//		compatibilityList.put(0x3171, "");
//		compatibilityList.put(0x3172, "");
//		compatibilityList.put(0x3173, "");
//		compatibilityList.put(0x3174, "");
//		compatibilityList.put(0x3175, "");
//		compatibilityList.put(0x3176, "");
//		compatibilityList.put(0x3177, "");
//		compatibilityList.put(0x3178, "");
//		compatibilityList.put(0x3179, "");
//		compatibilityList.put(0x317a, "");
//		compatibilityList.put(0x317b, "");
//		compatibilityList.put(0x317c, "");
//		compatibilityList.put(0x317d, "");
//		compatibilityList.put(0x317e, "");
//		compatibilityList.put(0x317f, "");
//		compatibilityList.put(0x3180, "");
//		compatibilityList.put(0x3181, "");
//		compatibilityList.put(0x3182, "");
//		compatibilityList.put(0x3183, "");
//		compatibilityList.put(0x3184, "");
        compatibilityList.put(0x3185, "gg");
//		compatibilityList.put(0x3186, "");
        compatibilityList.put(0x3187, "yi");
        compatibilityList.put(0x3188, "yO");
        compatibilityList.put(0x3189, "yl");
        compatibilityList.put(0x318a, "bu");
        compatibilityList.put(0x318b, "bP");
        compatibilityList.put(0x318c, "bl");
//		compatibilityList.put(0x318d, "");
//		compatibilityList.put(0x318e, "");

    }

    /**
     * 글자 표기
     */
    public class _CharZone{
        /** 초성 */
        private int initialConsonant;
        /** 중성 */
        private int syllableNucleus;
        /** 종성 */
        private int finalConsonant;
        /** Single 초성 */
        private int compatibility;
        /** 한글 아님 */
        private int notKorean;

        /**
         * @Override
         * 해당 키 값으로 변환 하도록 한다.
         */
        @Override
        public String toString(){

            if( notKorean > 0 ){
                return String.valueOf( (char)notKorean );
            }

            String result;

            try {
                if( compatibility > 0 ){
                    return compatibilityList.get( compatibility );
                }

                result  = initialConsonantList.get( initialConsonant );
                result += syllableNucleusList.get( syllableNucleus );

                if( finalConsonant > 0 ){
                    result += finalConsonantList.get( finalConsonant );
                }
            } catch (Exception e) {
                System.err.println("Convert Error : " + e.getMessage() );
                result = "";
            }

            return result;
        }

    }

    public List<_CharZone> divideKoreanTypo( String typo ){
        // 리스트로 받아오기 위한 변수
        final List<_CharZone> result = new ArrayList<_CharZone>();

        // typo 스트링의 글자수 만큼 lst에 담아둡니다.
        for( int i=0 ; i<typo.length() ; i++ ){
            final _CharZone cz = new _CharZone();
            result.add(cz);

            final int comVal = (int)typo.charAt(i);

            // 한글을 검색한다.
            if( (comVal >= 0xAC00) && (comVal <= 0xD79F) ){
                final int uniVal = (int)(comVal-0xAC00);
                final int uniIndex = (int)(uniVal%PHONOGRAM);

                // 유니코드표에 맞추어 초성 중성 종성을 분리한다.
                final int cho  = (int)(((( uniVal - uniIndex )/PHONOGRAM)/VOWEL) + 0x1100);
                final int jung = (int)(((( uniVal - uniIndex )/PHONOGRAM)%VOWEL) + 0x1161);
                final int jong = (int)(    uniIndex + 0x11A7 );

                cz.initialConsonant = cho;
                cz.syllableNucleus  = jung;
                cz.finalConsonant   = ( uniIndex != 0 )? jong:0;

            }else if( (comVal >=0x3130) && (comVal < 0x318F) ){
                // 초성 또는 중성만 있을 경우
                cz.compatibility = comVal;
            } else {
                // 한글이 아닌 것들...
                cz.notKorean = comVal;
            }
        }

        return result;
    }

    /**
     * 한글을 입력 받아 해당 영문 키 값으로 변환한다.
     * @param sHan 변환할 내용
     * @return 변환된 키 내용.
     */
    public String getHanKeyToEngKey( final String sHan ){

        final List<_CharZone> lst = this.divideKoreanTypo(sHan);

        final StringBuilder sb = new StringBuilder();

        for( _CharZone item : lst ){
            sb.append( item );
        }

        return sb.toString();
    }


    /**
     * test
     * @param args
     */
//    public static void main(String[] args) {
//
//        final HanKeyToEngKey hanToEngKey = HanKeyToEngKey.getInstance();
//
//        System.out.println( hanToEngKey.getHanKeyToEngKey("난각솟슠ㄱㄴㅏ Abc 03＠¼") );
//
//// 결과 [sksrkrthttbzrsk Abc 03＠¼]
//    }

}
