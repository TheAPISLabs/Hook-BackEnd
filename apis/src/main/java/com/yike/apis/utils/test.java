package com.yike.apis.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import org.web3j.protocol.Web3j;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {
    public static List<Integer[]> twoSum(Integer[] nums, Integer target) {
        List<Integer[]> list = new ArrayList();
        Integer[] res = new Integer[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(Integer i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                res[0]=map.get(nums[i]);
                res[1]=i;
                list.add(res);
            }
            map.put(target-nums[i], i);
        }
        return list;
    }

    public static void main(String[] args) throws ParseException {
//        Integer[] nums = {1,3,5,7,9,2,4,6,8};
//        Integer target = 9;
//        List<Integer[]> list = new ArrayList<>();
//        list = twoSum(nums,target);
//        System.out.println(list);
//        String json = "{\n" +
//                "    \"method\": \"qn_fetchNFTsByCreator\",\n" +
//                "    \"id\": 12,\n" +
//                "    \"params\": [\n" +
//                "      {\n" +
//                "        \"creator\": \"DznU28LgherhU2JwC2db3KmAeWPqoF9Yx2aVtNUudW6R\",\n" +
//                "        \"page\": 1,\n" +
//                "        \"perPage\": 3\n" +
//                "      }\n" +
//                "    ]\n" +
//                "  }";
//        String str = HttpRequest.post("http://sample-endpoint-name.network.quiknode.pro/token-goes-here/")
//                .body(json)
//                .execute().body();
        //随机生成密钥
        
//        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.simpleUUID());
        System.out.println(IdUtil.simpleUUID());
//        String s = "[UserVo(uId=b6a5264ac8b448a3a7f8f4388c6de31c, userIcon=https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fbao%2Fuploaded%2Fi1%2F88278447%2FO1CN01XKAlVD2CGmFQQ1qJr_%21%2188278447.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1656230268&t=0ac928b), UserVo(uId=b6a5264ac8b448a3a7f8f4388c6de31c, userIcon=https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fbao%2Fuploaded%2Fi1%2F88278447%2FO1CN01XKAlVD2CGmFQQ1qJr_%21%2188278447.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1656230268&t=0ac928b)]";
//        System.out.println(s.indexOf("b6a5264ac8b448a3a7f8f4388c6de31c"));

//        String str = "[{\n" +
//                "\t\t\"Address\": \"0xCd318BDF61690Aa45016aD027d25990663472437\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x6ccd77176809d2e5d36e242215b095d32443beb7\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x421ac37Bbf088f3ccb3AC949fe2349fD3b99c475\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x1e32fe46c0f5d85595dcdd7feac585598cf7675b\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x63296f9c79CA9098094F17593f39b89ac836C797\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xE30D05300dB6a5136648Ea6FB4c2E92678632e00\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x87Be9Ef0047976F280cEd9575CcE02f62952F0bE\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xdD4cC392E2d0Db783938B0F215581c21A707b2B9\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xF40DF66FE5B67848a91c96B0c59223C63d3478DA\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x0131aFdd3343fFD5488812a0491D47707234baA5\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x40916e43f9a767B2aD25bBB47B21CB0057eAfc8e\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xbe987A6Ea0543CDEbE7f795a67953b7882663332\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x403F9A7fe31997FFAf36569d6f9D775b548a915F\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x182381BEebCDB18461075D7F79D422364aF15503\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xA1c67f716442Bb009517b7B07A10b97aD5b6Bc9d\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xa2B53C436CB5aC7ED1F3582977f894f1e7F7491b\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x47D5Fe8988C763A7aCDc8Bac2204d6286e321e97\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xB0b74B4C4582561824396AFfC3C5551E77c7981E\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x3f9eef7D2D9f1A02dc5D3d861f7Bcda98073E3a4\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x9B95a077296C3485606Ac213c29DC0F1aCB662f1\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xc2a82A0636F7869Cf018EFd713A7d8F4b1Fb1f7f\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xdCba2c0AfB5c0e24559F5EE03a459486EFB1707E\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x9671e4f0f65Ae321e93dcec87CB5613B6AbD99E5\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xD959Fa2459E7bdDAdE564188F87D88159C4A7875\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x602990BbeA47e2748f1BBBDaac022aAd4d8985E0\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x42806e0a4e0933f2491BFCc0892507E58083f00b\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xB15B76Eb1F6b380Fea4FA376a110c1886a446e54\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xc05cC603bc49e772B23D1fB5CE1064D54df7ff72\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x4bc8070f9E89fb9f84599f196B0288fce0AA7ECA\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x338F070F2D4D22e561D8C149d12712fC206AC157\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x29eaE26D5790C49c67F8ABe8deEe8D1a8A821457\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x3937D13Ef725aF67A1820866C2CC4a020666c5B1\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x5DF898562064aA25f8e308110f0eAAe5dE79aeC2\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x7277fdf71035fF5d11792D41d77cf6CB9398f3Ed\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x94bf625A16e696cBb6aC776d498435782b37AF88\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xb52c32e4E73b896eCAB40d7ce3B9DcCCd1A1416C\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x290e73C26938C6a8C4BB045145Da6FAC9fAaeDa4\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x6d8E4e09F48f3459384Fd608c283DaC5AE7cf4a3\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xfb5cEA5E86eD98b4A624c2d927b66D3924644315\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x28d9A43381bBdb43C303E410755dE48A6DEef8bE\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xeDb140A38642327fBbF28C93556E15d6d1FdD2DD\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x6F6F8F19B073D3D1f1DC2665304cf4d7054713f9\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x11611e2C6f0c3830D6f902De518f3AcE36375B44\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x2A0B6Cb17b73748CB7eFdbe14d87fACfbB842f14\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xFc592f4C1655765F336075269ec47684Ec437D52\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xb6cdD64c84a018E3eD1bD037B7ae8Ae790fDefC6\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x13f9A1f579cCca6E1138Eb91D2B2F8EECe50D565\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x28F64b43ac50366BF5430c514A70C7Ac8F9087bc\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x67057522Acb395fc1a168922Cc80c0B2C96d8E93\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x9426a135A98b1e64b8fB720b44F8E7d18162DD79\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xf9f002047ABd16a89565D6083f8E59732C5019f4\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x9D63b1AD4A48c4C007B75C07950ed6A697D56745\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x6522320211353657287315b9016a9281E078f6Db\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x6397Dd81149a81F135Af164D7575959DA73AD1F7\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x08553679999DbAE5936AdF927f7cf23fbc0B56E4\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xF8Dc5a03Bb50E5c7697e442a7F4A289468891688\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xBA94A4590124C9756C606746267AdF8ee978bf75\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x2Af4AcD65172fCb9310c02211d6E52B0466BB8DB\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x5a4ded8a651755bbb5e5a8eb1a66bdf142af39fe\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xA8ef84d8b661d9450fD54610f63DB25A119E0792\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x7cf41d8cd91eebd357f705117983b3f8512957a4\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x8e1efa635476b76b9fc0d97f9ee781095644612c\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xc4532188c95b07aae8ffb2bf5437d07ba01393e7\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x96B260086DA2075d1FB5372379175e1854dF6b16\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xBc8fEc283FbEA0723B17B4e7f5C39185De02DBc3\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xD97A8c0b4629c3Fd761c5212C2ecE72aF5D14c26\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x63e73058e65Ba0FE3f925d2948988782d2617ab2\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xCb53269849c9C2536c21D2BFF78cFE92972C477F\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x2467EdEEC6dDD9ab8643eE1A5204e8A31BDFeBaF\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x41f71Be2646a5ba76769e4C70F6b79d144bC830a\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x8448AEBd662760D0C6Dd85a8779D326DbAa56e6D\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x54b39998079FfDdfd36a30801CC690cCfB1A4d31\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0xDb075171fd5B6C45644900bDBCD2FAa164d1f99d\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x2F46Cc0dAEB82F38073d4Fd785cB1acc94B5e539\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x5FAf8A143A8266E4d3224F8334A3Fc31e9dAC857\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x868f9a8b7e9092571f4c94f6706eD239aa9E3ec6\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x9Fe25FF86DDFa61824bb51Ef2ceE509F6404432b\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x9ae62bf7b350d281cb23ce879ffd8367aee078ba\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x3a46eb4329c9aa4cd5a373cdd0f7e83dd7150090\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x1c7Cb8b05bb1fb45aF3D95fC1b33e74d178b18ae\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x40aAb7503C52302382743Dd42844BEc1AA097bBe\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x4bF108F8ba214D3Ab7370B4Ef74EdC8834978dBD\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x370b439b9E73067F14E7CD67DCa4cC99F2A952DA\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x4ab7FA476f86C7370f1536D84e6a1EcaE1D3870d\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x5d8f151e3b0b424648cc8d30612e2fe9f4d3e97d\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x67c4d19d8f7f550972d51f2dcee7bb942ae0899e\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x8C3CE57c3862e4b7b068DA18885f72dc8aa9d12d\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x500411Fd1E0958D01c45B7AD8A031D78a535c54a\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x67e5e2466d2d847270431deed50b3aa8c6c1fe95\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x740949CdD447aE8c9a02Cbdc8727157C9458e7c6\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x3cae328e14a9b102ad9020b78215f68ef5773387\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x2bc291899B61d87fD31DaaFeE12a384a022B1C69\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x1b0f2f40b5bf97f0c099ec0cf93ee481c3fdd43d\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x3fd6BEf1F798c7bb92302205364051fBab858544\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x4c4d7376E8633CD0443fF0f2055f126d43FD9e23\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x43161C9de5A6Ff2F6181235a94Da1225541E2Cce\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x879b9d574350CD2780f44c3b6ac7F58EDc8Bca0f\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x4ab7FA476f86C7370f1536D84e6a1EcaE1D3870d\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x924b12a9302f96bf53e6ae6365Afb47eADD32246\"\n" +
//                "\t},\n" +
//                "\t{\n" +
//                "\t\t\"Address\": \"0x37d442F70F326058Ee41e415aD62D65E42Ee40aD\"\n" +
//                "\t}\n" +
//                "]";
//        JSONArray jsonArray = JSONUtil.parseArray(str);
//        List<Map> list = JSONUtil.toList(jsonArray,Map.class);
//        String[] s = new String[list.size()];
//        int i = 0;
//        for(Map map:list){
//            s[i] = map.get("Address").toString();
//            i++;
//        }
//        System.out.println(Arrays.toString(s));
    }
}
