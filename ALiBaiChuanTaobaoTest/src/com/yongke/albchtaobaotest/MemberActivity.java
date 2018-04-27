package com.yongke.albchtaobaotest;

import java.util.Map;
import java.util.Map.Entry;

import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.ResultCode;
import com.alibaba.sdk.android.globaltrade.GlobalItemService;
import com.alibaba.sdk.android.login.LoginService;
import com.alibaba.sdk.android.login.callback.LoginCallback;
import com.alibaba.sdk.android.login.callback.LogoutCallback;
import com.alibaba.sdk.android.session.model.Session;
import com.alibaba.sdk.android.trade.callback.TradeProcessCallback;
import com.alibaba.sdk.android.trade.model.TaokeParams;
import com.alibaba.sdk.android.trade.model.TradeResult;
import com.alibaba.sdk.android.ui.support.WebViewActivitySupport;
import com.ta.utdid2.android.utils.StringUtils;
import com.taobao.munion.base.Log;

public class MemberActivity extends AbstractActivity {

	private TextView tv;
	private StringBuffer sbString = new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member);
		//tv = (TextView) this.findViewById(R.id.tv);

		// 商品详情服务
		// ItemService service = AlibabaSDK.getService(ItemService.class);
		// //订单服务
		// OrderService service = AlibabaSDK.getService(OrderService.class);
		// //购物车服务
		// CartService service = AlibabaSDK.getService(CartService.class);
		// //营销服务
		// PromotionService service =
		// AlibabaSDK.getService(PromotionService.class);
		// //淘宝全球商品详情服务
		// GlobalItemService service =
		// AlibabaSDK.getService(GlobalItemService.class);

	}

	// authenticate & authorize
	public void showLogin(View view) {
		TaoOpen.startOauth(MemberActivity.this, "23217853",
				"0072bb6a9246ad9667866eb41eb13789");
		// TaoOpen.startOauth(MemberActivity.this, "23006411",
		// "0176ed8477d01e631d2ff5982dc08dd2");
		AlibabaSDK.getService(LoginService.class).showLogin(
				MemberActivity.this, new InternalLoginCallback());
	}

	public void logout(View view) {
		AlibabaSDK.getService(LoginService.class).logout(this,
				new LogoutCallback() {

					@Override
					public void onFailure(int code, String msg) {
						Toast.makeText(MemberActivity.this, "登出失败" + code,
								Toast.LENGTH_SHORT).show();

					}

					@Override
					public void onSuccess() {
						Toast.makeText(MemberActivity.this, "已经等了成功登出成功",
								Toast.LENGTH_SHORT).show();
						Log.i("TAG", "onSuccess====");
					}
				});
	}

	public void showQrLogin(View view) {
		AlibabaSDK.getService(LoginService.class).showQrCodeLogin(this,
				new InternalLoginCallback());
	}

	private class InternalLoginCallback implements LoginCallback {

		@Override
		public void onSuccess(Session session) {
			Log.i("TAG", "onSuccess=");
			sbString.append("UserId=" + session.getUserId()).append("\r\n");
			sbString.append("getUser().nick=" + session.getUser().nick).append(
					"\r\n");
			sbString.append("getLoginTime=" + session.getLoginTime()).append(
					"\r\n");
			sbString.append("isLogin=" + session.isLogin()).append("\r\n");
			sbString.append("getUser=" + session.getUser().avatarUrl).append(
					"\r\n");

			Log.i("TAG", "result=" + sbString.toString());
			Toast.makeText(MemberActivity.this, sbString.toString(),
					Toast.LENGTH_SHORT).show();
			tv.setText(sbString.toString());
			CookieManager.getInstance().removeAllCookie();
			CookieSyncManager.getInstance().sync();
			Map<String, String[]> m = WebViewActivitySupport.getInstance()
					.getCookies();
			for (Entry<String, String[]> e : m.entrySet()) {
				for (String s : e.getValue()) {
					CookieManager.getInstance().setCookie(e.getKey(), s);
					System.out.println("key: " + e.getKey() + " value: " + s);
				}
			}
			CookieSyncManager.getInstance().sync();
			System.out.println("------------"
					+ CookieManager.getInstance()
							.getCookie("http://taobao.com"));
		}

		@Override
		public void onFailure(int code, String message) {
			Toast.makeText(MemberActivity.this, "授权取消" + code,
					Toast.LENGTH_SHORT).show();
		}
	}

	public void showTaokeItemDetailGlobal(View view) {

		// TaoOpen.startOauth(this, "23015524",
		// "4d0220bc382eb628b612956fb8edeb00");

		TaoOpen.startOauth(MemberActivity.this, "23217853",
				"0072bb6a9246ad9667866eb41eb13789");

		EditText textView = (EditText) this
				.findViewById(R.id.itemInputDataGlobalV2);
		String inputData = textView.getText().toString();
		String[] inputDatas = new String[1];

		inputDatas[0] = "44235377131";

		if (!StringUtils.isEmpty(inputData)) {
			inputDatas[0] = inputData;
		}

		TaokeParams taokeParams = new TaokeParams();
		taokeParams.pid = "mm_26632322_6858406_23810104";
		AlibabaSDK.getService(GlobalItemService.class)
				.showTaokeItemDetailByItemId(this, new TradeProcessCallback() {

					@Override
					public void onPaySuccess(TradeResult tradeResult) {
						Toast.makeText(MemberActivity.this, "支付成功",
								Toast.LENGTH_SHORT).show();

					}

					@Override
					public void onFailure(int code, String msg) {
						if (code == ResultCode.QUERY_ORDER_RESULT_EXCEPTION.code) {
							Toast.makeText(MemberActivity.this, "确认交易订单失败",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(MemberActivity.this, "交易取消" + code,
									Toast.LENGTH_SHORT).show();
						}
					}
				}, null, Long.parseLong(inputDatas[0]), 1, null, taokeParams);
	}

}
