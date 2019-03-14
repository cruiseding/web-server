package com.net.aipeng.redirect.resolverD.interface4.zhufubao;

import java.util.Map;

import com.net.aipeng.classroot.interface4.JyLogDetect;
import com.net.aipeng.classroot.interface4.JyLogDetect.DataType;

//import com.net.feimiaoquan.classroot.interface4.JyLogDetect;
//import com.net.feimiaoquan.classroot.interface4.JyLogDetect.DataType;
//

public class Ordercreat {

	/** 支付宝支付业务：入参app_id */
	public static final String APPID = "2018091661473114";
	// public static final String APPID = "2016092800617116";
	// 支付宝公钥
	public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwSEJrnQriBBuAfc5rdF+Og+tePvPeXrMWDCZibhZ4jUZfb51JDmK8NEodg2w03JAfqwmJxAJHXIHGF8Tyl6m68RiPza+2tGh8ce3Z+4nObqYe+JjrHSHePTSW6CiJW2pT+waKnh4nQhEqwGsDc66+YTP8T08Gmg1Ws8C4TIAT0GqSULoDdX51N79bJMnS14bfQR/ajP5Arh5sgKuM03Ikt2HtgrVoisPnslk4Z75ylb9XRHJaMcizxEynrPPGc3Fg0oT7oCf4dlGbjuQO5lX76011/otMDk7w2mQQCqPy9GI3ANPHZMde5ZxK74S0rNsw8Wg6O5hTtr/aj9YtA+3/QIDAQAB";

	/** 支付宝账户登录授权业务：入参pid值 */
	public static final String PID = "";
	/** 支付宝账户登录授权业务：入参target_id值 */
	public static final String TARGET_ID = "";

	/** 商户私钥，pkcs8格式 */
	/** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
	/** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
	/** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
	/** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
	/**
	 * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
	 */
	// 支付宝商户私钥
	public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCxU06Io/Mcvwqe1kq1wg/nbBATSDhjOhgTPCdnqnsc+zMqnndG4W/ZXO8K5H3MJDWtMQpsbAHK7hd0Io3XlF+orcL6UfJK27kuVrxA2UaVSz037a320otwKv439f6MVD4EX6Jx2hLFWAA8OaOaunIB5toWjyMVRo6p+8Moci8h4ZwAQxgw2jf05NHVnFPHG9IgSJTH2tr0GOT7ZhlsifKNDdQFHxUBcQ9g+e6Gr6xHSRCzo+RyRwlFRSgD/qK6GqSxP7fHiUaVlY8/pU8bCs4O+E4X+p/tqK9aMmSJrh7DUdXtBzj45YjLK0jvbJ/ubUmx80GPlU4YjLNZK3tSRAS7AgMBAAECggEAbgMkHuuwQW5YlXCXSypJmabWVlAhA5agJxbTz6HZ3YIPHWrQzz2/mZfpidV2Ijd4walCMT/U4ORT0q45V7zqmAithYVfj4e/3HrdMlJaUvcp1M/1dMLs1TsnGurX8JG3JBl8+6typ9JP2w1bbfwsDPf+564H8wQiMF1Ml4Q3r4lzP9XvgpQnrRVfED7lZtgm2rHdl+kOVYKqQSCQVCP3+VxHqCkBALDEOt+JawcQjXLnhJlb4Fk17yyJl9Lc43+ION1tATr9duxi9fHBo++bRydJ9/4/IXB7BqAzLv+MEpTxowSfO4jPI/TqQjrkmpM5+3kOa0n6vKxKJvxklmKdaQKBgQDaRKk0Y7ZTbErQO5eVZmbZWFSdHpiV30JSTV3DP0BJw0A3fOrCkmpAbLprXzfw9gNsRZzcio2vzo79G2PDRnjE2T9VjXvhK+mjUbaHBVfar9pFlHMSIDhVioXNvHIUSB3JY5f3DQcGCWM59t5YM4tEaC9s79AmU/4GYDv/F8AiPwKBgQDP+r7Ip6XlftRYuB8KApsO6dXBkKYiKKI29QL07TVvW6LArRiq8tWKZXM/NsCDTxWYfsUf9XFrStYSgIMebLkAmcpP6b/vYI410gK0oZgiLfVyXWkZHAMwgczti008UL9aQH86xI+yNQWQvHHAupAXEHzQzNLf75ReXJu47qtGhQKBgQClDCh60i+r04abAYT1VzLMhUuuZ9Vv9xv6ikAthSXC4V8s1kvbB8ZKoy0wRhdSIWWjONyNtEPBvktHv69nmWF3UOPgeJVFRuRsODzyyqqNVk6SMNf2y+BtZssZMLd++K11fNcL3JEEEpMy4KdvQMK93p6GHK1kXj0HazvTAI2sFwKBgBear7h6PnvTbyo6i1tJfGuu4lG7pv9NeUufdxxxJ5Vh4xDXFGuib9XrOa88+ZBtk1fhW2ILKzowHievajIzyLNmwO1c7Cmw3bMCCMiTZn7KlIbMTzdIVJxWvvley/9CctU9aLM0x9JD7kPE/2wOQHg9qMzR0kUUHdSv0gSlIc85AoGBAJWkJfVQRdIT558/7qzwxX6BhEQkcNltUcf49R1i78X/l14J8RSgL51knuKXrYiyypaJTV5r6PLEQHlB3V0SJQxy9MzZEKqo1NAohzLGBPEsiMHnaGPqGx2llVxdDvy6TEHz5gD6thS1BXMQ04AwbOs81B4ZIcCbTIpgSsWQHnz4";
	// public static final String RSA_PRIVATE =
	// "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDHJZjKhN2eGCzHeDaZMkf4OHbpaFAeh0xlZNHtBVlbJZ009ObF0zrEYlemFbY0tdFd6F+NfvwLYf7eWglK+EytGBtJ+avdLUpUbKmxte9TB+CXqKm2fnqT09rBOoxx9PZIsnje4XUlqKMXxdByNYpQKvL1PmsJPpiuMQhzQ2X68R+UvjxH0nCkUIs+chcjVX06Dw/A3UaEIYVWJFwLoY1ttA+zQGia6Nli9N9AiOw5xqhmXmCjcPGR1pFRwM5htXdkox7i8MUziJDok6muWnNMsG6zPthO1x6Sx3BVRgXlTr9Sh7Kxl9umxjJjm3lrVA5ji+SVnMy+NJ39btPdiefLAgMBAAECggEAUpd5IqSv9N051RA6NJbx2jQwvOSSNdcPDA4gMhjB0ldrN4dKHklYWhIfH7ZpdrrhNf0lg91iUqY1uanFqN4JjtAsoSV27afW5+SZDCbUNq9RqhQ9Ln6N/7khRHovvSNG8SdzkZwXpaKm4TkdFgC2eEEXujZE1fSsBbI4OvV82fFi8GcPqSDr7iV4JO8yQhXjYppsmjGJckzoh44yHObi5JFNkhZMXuS9OHZ/f7eWWSO2/fyhUoUFHS6vKE9SOVLDgDXFKicXmJBM0J0EsijN0SpXKwLIzQaDQ7U/Kv9gf0Mdo7MlicXkh/vt+C0qk7KUkAesyrKk8tSmwUss7X2bwQKBgQDwDHr7hNQ0zlyDBPTQp2oTP0/ENPRQRkDRIH+SEZ6Q6lvrlqOkImQIISsW9h6fcYMjFBF0vTIIeat4h8aXV3Rw4XT+8SPgJe2s26xNIHkFWayaqv9AOTNa37uPR13/V0QgY4fv6Fa1n0+gU1cgyPdBVbx6sG5gV99u4p7ePSZH0wKBgQDUYVNqM7+Ib/RZgQo5ZhrB3rzkMubuSzroiRFQckKVU7i4EPjb7lE9VaU8/HmJ6+wdNcjD0ksyKaa+AJUcGLY5TsM6gYb0M56u2FSH2asHANcRR1XS7y4kIDkZc32/9xUV/zq/dkzOV6F2te5Ys5Gn/rzx/ea7UIEFwdgTiLSdKQKBgHc8K8ZNZz7Hlio6RwRxc/4Vf6CdERHio4V9GADNIa8nyfrOe4Am2Ps0xnuESxe1wYamDDmpVHXjQan//0PjW+JKCtwrmT9Yd6NaBk7VJ2fh8BtvfpQ/FWQ5J6f2shkIFr3j0nz3MkLK3DltzCRrzm00kgd7JYhTfZdNl84aH66jAoGAS80c95fWsby7qsJqwsfVnwJ/yrb8Q2rocJyTaHIPn7qNAG1WVTD/H/QUkOrP+lERKe/LY1EIc7P4BnKwxaFwUI3zEkuuvjQ6W89shDxFVa+QE2OxlyK1BkHV/2aAKGboyLwa3ME9cpxGTddrZY9Z2msKRxjHJG38TvC7zWUKidkCgYEAnCc95bGVk4FLZmp+eBtRPgGdnBEWVccpcQg5u9CLjs0txwzGMD1ceXDY3zfLoowFgTgim7chSCQHfDoV2yx+tx3+afbQ4frD/dBhNVJLs9idcQErlJJ5W9/TSVR3+X6DuWWq/hC4P1BSxWhFuqgA94KmlsXi+k85HWwHR/MNNkk=";
	public static final String RSA_PRIVATE = "";
	// public static final String RSA2_PRIVATE ="";
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_AUTH_FLAG = 2;

	public static String order_creat(String content) {

		/**
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成； 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
		 * 
		 * orderInfo的获取必须来自服务端；
		 */
		JyLogDetect log = new JyLogDetect();
		boolean rsa2 = (RSA2_PRIVATE.length() > 0);
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2, content);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
		log.send(DataType.basicType, "01178", "orderParam::::::::", orderParam);

		String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
		String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
		log.send(DataType.basicType, "01178", "sign::::::::", sign);
		String orderInfo = orderParam + "&" + sign;
		return orderInfo;
	}

}
