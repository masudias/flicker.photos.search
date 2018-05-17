package com.masudias.flickerdashboard.util;

import android.content.Context;

import com.google.gson.Gson;
import com.masudias.flickerdashboard.database.DataHelper;
import com.masudias.flickerdashboard.domain.db.Photo;
import com.masudias.flickerdashboard.domain.http.FlickerPhoto;
import com.masudias.flickerdashboard.domain.http.response.FlickerSearchResponse;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    private static String dummyDataFromFlicker = "{\n" +
            "    \"photos\": {\n" +
            "        \"page\": 1,\n" +
            "        \"pages\": 3592,\n" +
            "        \"perpage\": 100,\n" +
            "        \"total\": \"359126\",\n" +
            "        \"photo\": [\n" +
            "            {\n" +
            "                \"id\": \"40360743540\",\n" +
            "                \"owner\": \"141738586@N02\",\n" +
            "                \"secret\": \"277f4bea89\",\n" +
            "                \"server\": \"943\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"At the Volvo Ocean Race, Teammates Turn Into Rivals, for Now\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/943/40360743540_f54a444ded_o.jpg\",\n" +
            "                \"height_o\": \"293\",\n" +
            "                \"width_o\": \"440\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42167822971\",\n" +
            "                \"owner\": \"141738586@N02\",\n" +
            "                \"secret\": \"500edd2e37\",\n" +
            "                \"server\": \"910\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Sailing Around the World, in His Father’s Footsteps\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/910/42167822971_01923c3c73_o.jpg\",\n" +
            "                \"height_o\": \"293\",\n" +
            "                \"width_o\": \"440\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42121177332\",\n" +
            "                \"owner\": \"163394394@N04\",\n" +
            "                \"secret\": \"05e1a26dd9\",\n" +
            "                \"server\": \"953\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Any one need NewsApp?\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/953/42121177332_47b6241022_o.jpg\",\n" +
            "                \"height_o\": \"788\",\n" +
            "                \"width_o\": \"940\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28275111068\",\n" +
            "                \"owner\": \"75019066@N00\",\n" +
            "                \"secret\": \"1d67ae7cea\",\n" +
            "                \"server\": \"979\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"nikon 382b\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41246522455\",\n" +
            "                \"owner\": \"75019066@N00\",\n" +
            "                \"secret\": \"338e6d1d78\",\n" +
            "                \"server\": \"832\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"nikon 381b\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41427168614\",\n" +
            "                \"owner\": \"75019066@N00\",\n" +
            "                \"secret\": \"99a0cee429\",\n" +
            "                \"server\": \"911\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"nikon 380b\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27295515547\",\n" +
            "                \"owner\": \"80048637@N02\",\n" +
            "                \"secret\": \"03c5e3385e\",\n" +
            "                \"server\": \"943\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"#Cars 1935 MG PA at the 2017 British Wheels On the Green - Peoria, AZ [2214x3077] (OC)\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/943/27295515547_2701419cbc_o.jpg\",\n" +
            "                \"height_o\": \"1779\",\n" +
            "                \"width_o\": \"1280\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42120554812\",\n" +
            "                \"owner\": \"158178079@N08\",\n" +
            "                \"secret\": \"638f686a37\",\n" +
            "                \"server\": \"946\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Eat dirt,bitch\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/946/42120554812_55dba29bac_o.jpg\",\n" +
            "                \"height_o\": \"1200\",\n" +
            "                \"width_o\": \"868\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28294248998\",\n" +
            "                \"owner\": \"134052272@N07\",\n" +
            "                \"secret\": \"bedf06b060\",\n" +
            "                \"server\": \"970\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Blankenhorm - Rivas\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/970/28294248998_772d90d8a8_o.jpg\",\n" +
            "                \"height_o\": \"1189\",\n" +
            "                \"width_o\": \"1600\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42166484681\",\n" +
            "                \"owner\": \"158178079@N08\",\n" +
            "                \"secret\": \"7b8d867c6b\",\n" +
            "                \"server\": \"951\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Just plain dirty\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/951/42166484681_d1b9f8e444_o.jpg\",\n" +
            "                \"height_o\": \"1200\",\n" +
            "                \"width_o\": \"957\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42120167032\",\n" +
            "                \"owner\": \"158178079@N08\",\n" +
            "                \"secret\": \"415f06179b\",\n" +
            "                \"server\": \"954\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"55\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/954/42120167032_17c72a462f_o.jpg\",\n" +
            "                \"height_o\": \"1200\",\n" +
            "                \"width_o\": \"852\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27294870697\",\n" +
            "                \"owner\": \"158178079@N08\",\n" +
            "                \"secret\": \"c4131cb4d0\",\n" +
            "                \"server\": \"978\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"71\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/978/27294870697_e1a5c4f345_o.jpg\",\n" +
            "                \"height_o\": \"796\",\n" +
            "                \"width_o\": \"1200\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41265524795\",\n" +
            "                \"owner\": \"158178079@N08\",\n" +
            "                \"secret\": \"469a8f97ef\",\n" +
            "                \"server\": \"954\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Mud Regatta\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/954/41265524795_cff5122c84_o.jpg\",\n" +
            "                \"height_o\": \"1005\",\n" +
            "                \"width_o\": \"1082\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28293732878\",\n" +
            "                \"owner\": \"24090557@N03\",\n" +
            "                \"secret\": \"1996ce94bc\",\n" +
            "                \"server\": \"957\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Konstructive Cycles at the XCO UCI World Cup in the beautiful town of Albstadt. That's what a modern cross-country course looks like. :) Konstructive Cycles beim XCO UCI Weltcup im wunderschönen Albstadt. So sieht ein moderner Cross-Country Kurs von oben\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/957/28293732878_25bda9345d_o.jpg\",\n" +
            "                \"height_o\": \"733\",\n" +
            "                \"width_o\": \"640\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42166225091\",\n" +
            "                \"owner\": \"158178079@N08\",\n" +
            "                \"secret\": \"e405346e16\",\n" +
            "                \"server\": \"982\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"21\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/982/42166225091_69c27721f5_o.jpg\",\n" +
            "                \"height_o\": \"1200\",\n" +
            "                \"width_o\": \"706\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41445212294\",\n" +
            "                \"owner\": \"164592897@N06\",\n" +
            "                \"secret\": \"5ba2118517\",\n" +
            "                \"server\": \"982\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Cs4: Simple, Dynamic Slideshows - Portfolio - 6\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/982/41445212294_1913507358_o.jpg\",\n" +
            "                \"height_o\": \"1080\",\n" +
            "                \"width_o\": \"1920\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41265082995\",\n" +
            "                \"owner\": \"164592897@N06\",\n" +
            "                \"secret\": \"d1255476b5\",\n" +
            "                \"server\": \"949\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Cs4: Simple, Dynamic Slideshows - Portfolio - 6\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/949/41265082995_ca344a66a9_o.jpg\",\n" +
            "                \"height_o\": \"1080\",\n" +
            "                \"width_o\": \"1920\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"40358993940\",\n" +
            "                \"owner\": \"164592897@N06\",\n" +
            "                \"secret\": \"8bce6ee95c\",\n" +
            "                \"server\": \"975\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Cs4: Simple, Dynamic Slideshows - Portfolio - 6\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/975/40358993940_07c5407cdf_o.jpg\",\n" +
            "                \"height_o\": \"1080\",\n" +
            "                \"width_o\": \"1920\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41445208414\",\n" +
            "                \"owner\": \"157538243@N06\",\n" +
            "                \"secret\": \"fc28c4406e\",\n" +
            "                \"server\": \"945\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Quick Look at Chiropractic Sports Medicine\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/945/41445208414_a7ccdba078_o.png\",\n" +
            "                \"height_o\": \"2000\",\n" +
            "                \"width_o\": \"800\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28293155498\",\n" +
            "                \"owner\": \"103533263@N07\",\n" +
            "                \"secret\": \"6bc8635364\",\n" +
            "                \"server\": \"830\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Nick PERCAT ~ Timken Livery\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/830/28293155498_5a6e3d809e_o.jpg\",\n" +
            "                \"height_o\": \"2621\",\n" +
            "                \"width_o\": \"4659\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27294056907\",\n" +
            "                \"owner\": \"141738586@N02\",\n" +
            "                \"secret\": \"2199874595\",\n" +
            "                \"server\": \"954\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Lightning Lean on Their Stars to Strike Back Against the Capitals\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/954/27294056907_c860f6c3b2_o.jpg\",\n" +
            "                \"height_o\": \"293\",\n" +
            "                \"width_o\": \"440\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41264594615\",\n" +
            "                \"owner\": \"140445608@N05\",\n" +
            "                \"secret\": \"bb1ec9288f\",\n" +
            "                \"server\": \"831\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Mark Waugh slams 'selfish' India over day-night Test snub | Sports\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/831/41264594615_79a70edcf6_o.jpg\",\n" +
            "                \"height_o\": \"400\",\n" +
            "                \"width_o\": \"700\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42118900342\",\n" +
            "                \"owner\": \"152814849@N04\",\n" +
            "                \"secret\": \"90e792fd27\",\n" +
            "                \"server\": \"947\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"IPL 2018, RCB vs SRH: When and Where to Watch Live Cricket, Coverage on Star Sports and Live Streaming on Hotstar\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/947/42118900342_09c407854d_o.jpg\",\n" +
            "                \"height_o\": \"583\",\n" +
            "                \"width_o\": \"875\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42118704332\",\n" +
            "                \"owner\": \"80048637@N02\",\n" +
            "                \"secret\": \"19ecb4987f\",\n" +
            "                \"server\": \"966\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"#Cars A Ford garage in Amsterdam around 1975.\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/966/42118704332_e3bf99c7ee_o.jpg\",\n" +
            "                \"height_o\": \"671\",\n" +
            "                \"width_o\": \"960\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293255677\",\n" +
            "                \"owner\": \"23558432@N02\",\n" +
            "                \"secret\": \"ae978f98d4\",\n" +
            "                \"server\": \"974\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Park\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28292118508\",\n" +
            "                \"owner\": \"23558432@N02\",\n" +
            "                \"secret\": \"e8d174e7a3\",\n" +
            "                \"server\": \"964\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Mini-Ramp\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28292068978\",\n" +
            "                \"owner\": \"23558432@N02\",\n" +
            "                \"secret\": \"146bff751e\",\n" +
            "                \"server\": \"977\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Flat\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293153137\",\n" +
            "                \"owner\": \"23558432@N02\",\n" +
            "                \"secret\": \"1d7a864c41\",\n" +
            "                \"server\": \"943\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Street\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28292045818\",\n" +
            "                \"owner\": \"23558432@N02\",\n" +
            "                \"secret\": \"de0169ebf2\",\n" +
            "                \"server\": \"953\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Dirt\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28292296298\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"e2fd113a5e\",\n" +
            "                \"server\": \"824\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"001\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/824/28292296298_20ec1ba606_o.jpg\",\n" +
            "                \"height_o\": \"1475\",\n" +
            "                \"width_o\": \"1849\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"40358086060\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"d0ec0483ce\",\n" +
            "                \"server\": \"966\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"002\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/966/40358086060_5e6f5134cc_o.jpg\",\n" +
            "                \"height_o\": \"950\",\n" +
            "                \"width_o\": \"2428\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28292294478\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"a18fc71f9e\",\n" +
            "                \"server\": \"979\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"003\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/979/28292294478_7d0d38dffa_o.jpg\",\n" +
            "                \"height_o\": \"1169\",\n" +
            "                \"width_o\": \"2435\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41444239304\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"bf4e5819b0\",\n" +
            "                \"server\": \"963\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"004\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/963/41444239304_c88601364b_o.jpg\",\n" +
            "                \"height_o\": \"2281\",\n" +
            "                \"width_o\": \"2418\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42118475462\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"3a4e0de317\",\n" +
            "                \"server\": \"944\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"005\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/944/42118475462_e5cafc4ae2_o.jpg\",\n" +
            "                \"height_o\": \"1383\",\n" +
            "                \"width_o\": \"2418\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41444236614\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"1b80b0a180\",\n" +
            "                \"server\": \"947\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"006\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/947/41444236614_fe42963f48_o.jpg\",\n" +
            "                \"height_o\": \"1968\",\n" +
            "                \"width_o\": \"2458\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42118472612\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"3da891e2c6\",\n" +
            "                \"server\": \"980\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"007\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/980/42118472612_bcb73ffdc1_o.jpg\",\n" +
            "                \"height_o\": \"1426\",\n" +
            "                \"width_o\": \"2401\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41444234364\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"9c3975fb2c\",\n" +
            "                \"server\": \"950\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"008\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/950/41444234364_deda04c61f_o.jpg\",\n" +
            "                \"height_o\": \"2030\",\n" +
            "                \"width_o\": \"2348\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42118469522\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"4bc24f9b4f\",\n" +
            "                \"server\": \"964\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"009\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/964/42118469522_7e148fbecf_o.jpg\",\n" +
            "                \"height_o\": \"2063\",\n" +
            "                \"width_o\": \"1514\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41444231504\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"929f343e27\",\n" +
            "                \"server\": \"951\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"010\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/951/41444231504_b5d364c5e2_o.jpg\",\n" +
            "                \"height_o\": \"2063\",\n" +
            "                \"width_o\": \"1514\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42118467932\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"0dce4a7acd\",\n" +
            "                \"server\": \"963\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"011\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/963/42118467932_63cfafe90a_o.jpg\",\n" +
            "                \"height_o\": \"2237\",\n" +
            "                \"width_o\": \"1940\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41444229224\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"ae9252abb6\",\n" +
            "                \"server\": \"911\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"012\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/911/41444229224_29f4eeefb7_o.jpg\",\n" +
            "                \"height_o\": \"1458\",\n" +
            "                \"width_o\": \"2426\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164864661\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"ae7113439c\",\n" +
            "                \"server\": \"981\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"013\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/981/42164864661_3ec2b7f921_o.jpg\",\n" +
            "                \"height_o\": \"1065\",\n" +
            "                \"width_o\": \"2428\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41444226984\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"ba517ba87b\",\n" +
            "                \"server\": \"911\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"014\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/911/41444226984_1247d343e3_o.jpg\",\n" +
            "                \"height_o\": \"1463\",\n" +
            "                \"width_o\": \"2423\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164861381\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"631f8e1f4c\",\n" +
            "                \"server\": \"905\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"015\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/905/42164861381_ac5c7fc830_o.jpg\",\n" +
            "                \"height_o\": \"1230\",\n" +
            "                \"width_o\": \"2402\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164860281\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"f967efc2cd\",\n" +
            "                \"server\": \"967\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"016\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/967/42164860281_cc19b515ba_o.jpg\",\n" +
            "                \"height_o\": \"1158\",\n" +
            "                \"width_o\": \"2328\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164858841\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"26a4e77989\",\n" +
            "                \"server\": \"970\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"017\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/970/42164858841_a6d88d8cce_o.jpg\",\n" +
            "                \"height_o\": \"1406\",\n" +
            "                \"width_o\": \"2429\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164857141\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"464e94c610\",\n" +
            "                \"server\": \"971\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"018\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/971/42164857141_11efb5efe6_o.jpg\",\n" +
            "                \"height_o\": \"2425\",\n" +
            "                \"width_o\": \"2137\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164854491\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"2851f164e2\",\n" +
            "                \"server\": \"824\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"019\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/824/42164854491_015ff60069_o.jpg\",\n" +
            "                \"height_o\": \"916\",\n" +
            "                \"width_o\": \"2402\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164853161\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"bfcc893ebd\",\n" +
            "                \"server\": \"964\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"020\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/964/42164853161_24cd450144_o.jpg\",\n" +
            "                \"height_o\": \"963\",\n" +
            "                \"width_o\": \"2403\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164851791\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"e7325ba18d\",\n" +
            "                \"server\": \"903\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"021\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/903/42164851791_3b9c17f62f_o.jpg\",\n" +
            "                \"height_o\": \"1850\",\n" +
            "                \"width_o\": \"2407\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164849511\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"c9f753cf8e\",\n" +
            "                \"server\": \"905\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"022\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/905/42164849511_ecf1cf132a_o.jpg\",\n" +
            "                \"height_o\": \"1812\",\n" +
            "                \"width_o\": \"2437\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164847911\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"4148707a60\",\n" +
            "                \"server\": \"978\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"023\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/978/42164847911_2e9013ec9b_o.jpg\",\n" +
            "                \"height_o\": \"1968\",\n" +
            "                \"width_o\": \"2438\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164846041\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"01a47826eb\",\n" +
            "                \"server\": \"825\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"024\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/825/42164846041_520006e583_o.jpg\",\n" +
            "                \"height_o\": \"1915\",\n" +
            "                \"width_o\": \"2413\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164843661\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"71bf4268ca\",\n" +
            "                \"server\": \"823\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"025\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/823/42164843661_e98d7149bc_o.jpg\",\n" +
            "                \"height_o\": \"1151\",\n" +
            "                \"width_o\": \"1390\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41264014055\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"1321f8dda4\",\n" +
            "                \"server\": \"977\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"026\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/977/41264014055_46f376dcc7_o.jpg\",\n" +
            "                \"height_o\": \"1151\",\n" +
            "                \"width_o\": \"1390\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164842781\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"8076e2f529\",\n" +
            "                \"server\": \"980\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"027\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/980/42164842781_7d02226a81_o.jpg\",\n" +
            "                \"height_o\": \"1341\",\n" +
            "                \"width_o\": \"2413\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41264012445\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"fb941a349f\",\n" +
            "                \"server\": \"910\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"028\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/910/41264012445_91665c3f1a_o.jpg\",\n" +
            "                \"height_o\": \"923\",\n" +
            "                \"width_o\": \"1461\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164841021\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"512c3711be\",\n" +
            "                \"server\": \"943\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"029\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/943/42164841021_e47733cf81_o.jpg\",\n" +
            "                \"height_o\": \"693\",\n" +
            "                \"width_o\": \"1040\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164840791\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"4bc9e1dc2a\",\n" +
            "                \"server\": \"832\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"031\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/832/42164840791_6e1eaed20b_o.jpg\",\n" +
            "                \"height_o\": \"1447\",\n" +
            "                \"width_o\": \"2406\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41264011275\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"aab8c7e5b3\",\n" +
            "                \"server\": \"903\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"030\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/903/41264011275_0525b8bebd_o.jpg\",\n" +
            "                \"height_o\": \"693\",\n" +
            "                \"width_o\": \"1040\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41264009405\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"e7fbddf3b4\",\n" +
            "                \"server\": \"975\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"032\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/975/41264009405_5062504574_o.jpg\",\n" +
            "                \"height_o\": \"1907\",\n" +
            "                \"width_o\": \"2406\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164838801\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"fcb1a6a60f\",\n" +
            "                \"server\": \"966\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"033\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/966/42164838801_b397f8eaf8_o.jpg\",\n" +
            "                \"height_o\": \"1606\",\n" +
            "                \"width_o\": \"2427\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41264005665\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"f9cf09bb69\",\n" +
            "                \"server\": \"972\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"034\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/972/41264005665_38277e77f7_o.jpg\",\n" +
            "                \"height_o\": \"1474\",\n" +
            "                \"width_o\": \"2420\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164836811\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"a3d48a0241\",\n" +
            "                \"server\": \"968\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"035\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/968/42164836811_5a0a6ed4f6_o.jpg\",\n" +
            "                \"height_o\": \"2072\",\n" +
            "                \"width_o\": \"2416\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41264001665\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"c692beb88e\",\n" +
            "                \"server\": \"963\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"036\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/963/41264001665_49d88d89ae_o.jpg\",\n" +
            "                \"height_o\": \"2428\",\n" +
            "                \"width_o\": \"1896\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164834661\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"c1e70b3f6e\",\n" +
            "                \"server\": \"828\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"037\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/828/42164834661_320f2429b4_o.jpg\",\n" +
            "                \"height_o\": \"1482\",\n" +
            "                \"width_o\": \"1386\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42164833981\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"3561ce079a\",\n" +
            "                \"server\": \"965\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"038\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/965/42164833981_178db788b0_o.jpg\",\n" +
            "                \"height_o\": \"1482\",\n" +
            "                \"width_o\": \"1386\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263998055\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"9a4cca9682\",\n" +
            "                \"server\": \"950\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"039\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/950/41263998055_8ef3eb9c7d_o.jpg\",\n" +
            "                \"height_o\": \"1439\",\n" +
            "                \"width_o\": \"2415\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263996015\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"73022581d2\",\n" +
            "                \"server\": \"831\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"040\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/831/41263996015_ae548650ec_o.jpg\",\n" +
            "                \"height_o\": \"1439\",\n" +
            "                \"width_o\": \"2415\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263993745\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"50d97abb43\",\n" +
            "                \"server\": \"944\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"042\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/944/41263993745_105ceecbb8_o.jpg\",\n" +
            "                \"height_o\": \"950\",\n" +
            "                \"width_o\": \"2302\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263992835\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"e2f5870916\",\n" +
            "                \"server\": \"826\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"043\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/826/41263992835_4b00a2a63e_o.jpg\",\n" +
            "                \"height_o\": \"950\",\n" +
            "                \"width_o\": \"2302\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263992205\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"80054d7f91\",\n" +
            "                \"server\": \"960\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"044\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/960/41263992205_f7bbb967a3_o.jpg\",\n" +
            "                \"height_o\": \"1748\",\n" +
            "                \"width_o\": \"2240\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28292253788\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"29df562a45\",\n" +
            "                \"server\": \"906\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"045\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/906/28292253788_ddaf337a0f_o.jpg\",\n" +
            "                \"height_o\": \"1748\",\n" +
            "                \"width_o\": \"2240\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263989955\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"156b7a95d3\",\n" +
            "                \"server\": \"957\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"046\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/957/41263989955_76cdc7a16b_o.jpg\",\n" +
            "                \"height_o\": \"1604\",\n" +
            "                \"width_o\": \"2403\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263988625\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"d068590aa9\",\n" +
            "                \"server\": \"962\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"048\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/962/41263988625_e981009ee5_o.jpg\",\n" +
            "                \"height_o\": \"1913\",\n" +
            "                \"width_o\": \"2355\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28292249768\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"048470ec79\",\n" +
            "                \"server\": \"823\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"049\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/823/28292249768_78649a112a_o.jpg\",\n" +
            "                \"height_o\": \"1744\",\n" +
            "                \"width_o\": \"2432\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263985885\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"62aeaf8bfc\",\n" +
            "                \"server\": \"828\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"050\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/828/41263985885_f5026f9466_o.jpg\",\n" +
            "                \"height_o\": \"1665\",\n" +
            "                \"width_o\": \"2401\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263984815\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"7225f97271\",\n" +
            "                \"server\": \"911\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"051\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/911/41263984815_7f4d9e4a69_o.jpg\",\n" +
            "                \"height_o\": \"1665\",\n" +
            "                \"width_o\": \"2401\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28292245378\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"6d590b85a3\",\n" +
            "                \"server\": \"972\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"052\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/972/28292245378_c30c8c90fb_o.jpg\",\n" +
            "                \"height_o\": \"2079\",\n" +
            "                \"width_o\": \"2404\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"28292243188\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"8d11f592aa\",\n" +
            "                \"server\": \"962\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"053\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/962/28292243188_b2255cde2d_o.jpg\",\n" +
            "                \"height_o\": \"1070\",\n" +
            "                \"width_o\": \"1518\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293309217\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"d70251148e\",\n" +
            "                \"server\": \"971\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"054\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/971/27293309217_e69b5825c6_o.jpg\",\n" +
            "                \"height_o\": \"1405\",\n" +
            "                \"width_o\": \"2411\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293308377\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"b55efb9f90\",\n" +
            "                \"server\": \"972\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"055\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/972/27293308377_e1b3e91cfe_o.jpg\",\n" +
            "                \"height_o\": \"1134\",\n" +
            "                \"width_o\": \"2400\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293307327\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"340a5c743a\",\n" +
            "                \"server\": \"828\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"056\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/828/27293307327_f233ed3329_o.jpg\",\n" +
            "                \"height_o\": \"1717\",\n" +
            "                \"width_o\": \"2322\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293305677\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"332552cd8c\",\n" +
            "                \"server\": \"977\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"057\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/977/27293305677_3aeebb2f6b_o.jpg\",\n" +
            "                \"height_o\": \"1888\",\n" +
            "                \"width_o\": \"2415\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42118421482\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"75349acd7d\",\n" +
            "                \"server\": \"975\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"058\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/975/42118421482_01f14e83b9_o.jpg\",\n" +
            "                \"height_o\": \"2414\",\n" +
            "                \"width_o\": \"2073\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293302807\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"2b409028a1\",\n" +
            "                \"server\": \"830\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"059\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/830/27293302807_e137c87a17_o.jpg\",\n" +
            "                \"height_o\": \"2143\",\n" +
            "                \"width_o\": \"2451\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42118418132\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"8d4b1d4cb2\",\n" +
            "                \"server\": \"827\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"060\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/827/42118418132_b4385e5dc8_o.jpg\",\n" +
            "                \"height_o\": \"2260\",\n" +
            "                \"width_o\": \"2430\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42118415692\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"d689572464\",\n" +
            "                \"server\": \"962\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"061\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/962/42118415692_b6edd8f714_o.jpg\",\n" +
            "                \"height_o\": \"2260\",\n" +
            "                \"width_o\": \"2430\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293296767\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"710db414b0\",\n" +
            "                \"server\": \"974\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"062\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/974/27293296767_847742a2d8_o.jpg\",\n" +
            "                \"height_o\": \"1852\",\n" +
            "                \"width_o\": \"2405\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42118412272\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"aced20ec53\",\n" +
            "                \"server\": \"981\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"063\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/981/42118412272_5f26edb52a_o.jpg\",\n" +
            "                \"height_o\": \"1750\",\n" +
            "                \"width_o\": \"2424\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293293097\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"185c472ef5\",\n" +
            "                \"server\": \"906\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"064\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/906/27293293097_2a1d643e98_o.jpg\",\n" +
            "                \"height_o\": \"1852\",\n" +
            "                \"width_o\": \"2419\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293291807\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"d25f78d489\",\n" +
            "                \"server\": \"904\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"065\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/904/27293291807_be036949d4_o.jpg\",\n" +
            "                \"height_o\": \"1776\",\n" +
            "                \"width_o\": \"2091\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263975465\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"27dd82d47d\",\n" +
            "                \"server\": \"831\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"066\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/831/41263975465_b723c3b4d9_o.jpg\",\n" +
            "                \"height_o\": \"1403\",\n" +
            "                \"width_o\": \"2421\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263974085\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"15e4f583be\",\n" +
            "                \"server\": \"953\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"068\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/953/41263974085_5dcde8441a_o.jpg\",\n" +
            "                \"height_o\": \"1346\",\n" +
            "                \"width_o\": \"2404\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293286927\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"4e8b382401\",\n" +
            "                \"server\": \"973\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"069\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/973/27293286927_3d6897d3ee_o.jpg\",\n" +
            "                \"height_o\": \"1660\",\n" +
            "                \"width_o\": \"1780\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41263972875\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"c2ba510197\",\n" +
            "                \"server\": \"908\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"070\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/908/41263972875_ef5aba8df5_o.jpg\",\n" +
            "                \"height_o\": \"1172\",\n" +
            "                \"width_o\": \"1221\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"27293285457\",\n" +
            "                \"owner\": \"59701752@N02\",\n" +
            "                \"secret\": \"67b2b8e15e\",\n" +
            "                \"server\": \"912\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"071\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/912/27293285457_0dfea743ef_o.jpg\",\n" +
            "                \"height_o\": \"1139\",\n" +
            "                \"width_o\": \"2740\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"41267248785\",\n" +
            "                \"owner\": \"80048637@N02\",\n" +
            "                \"secret\": \"e8e307a4e8\",\n" +
            "                \"server\": \"960\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"#Cars Golden? BMW M4\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/960/41267248785_979f55b7b0_o.jpg\",\n" +
            "                \"height_o\": \"866\",\n" +
            "                \"width_o\": \"1280\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"42167278331\",\n" +
            "                \"owner\": \"146361452@N07\",\n" +
            "                \"secret\": \"f4fea98bed\",\n" +
            "                \"server\": \"831\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"#4 Cris\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0,\n" +
            "                \"url_o\": \"https://farm1.staticflickr.com/831/42167278331_a04513cec8_o.jpg\",\n" +
            "                \"height_o\": \"3493\",\n" +
            "                \"width_o\": \"2329\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"40359376540\",\n" +
            "                \"owner\": \"18186662@N05\",\n" +
            "                \"secret\": \"4fb868d03c\",\n" +
            "                \"server\": \"958\",\n" +
            "                \"farm\": 1,\n" +
            "                \"title\": \"Donuts with Michael Metge\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    \"stat\": \"ok\"\n" +
            "}";

    public static void insertDummyDataIntoPhotosTable(Context context) {
        Gson gson = new Gson();
        FlickerSearchResponse response = gson.fromJson(dummyDataFromFlicker, FlickerSearchResponse.class);
        List<Photo> photoList = new ArrayList<Photo>();

        for (FlickerPhoto photo : response.photos.photo)
            photoList.add(photo.getPhoto());

        DataHelper.getInstance(context).insertPhotoListIntoDatabase(photoList);
    }
}
