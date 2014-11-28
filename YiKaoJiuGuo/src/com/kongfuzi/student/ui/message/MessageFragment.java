package com.kongfuzi.student.ui.message;

import java.util.List;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.google.gson.reflect.TypeToken;
import com.kongfuzi.lib.volley.Response.Listener;
import com.kongfuzi.student.R;
import com.kongfuzi.student.app.YiKaoApplication;
import com.kongfuzi.student.bean.Message;
import com.kongfuzi.student.support.network.ArrayRequest;
import com.kongfuzi.student.support.utils.UrlConstants;
import com.kongfuzi.student.ui.adapter.MessageAdapter;
import com.kongfuzi.student.ui.global.BaseFragment;

/**
 * @author LBDL
 * @desc 消息列表
 * 
 */
public class MessageFragment extends BaseFragment implements OnItemClickListener, IXListViewRefreshListener,
		IXListViewLoadMore {

	private XListView msg_xlv;

	private MessageAdapter adapter;

	public static MessageFragment getInstance() {

		MessageFragment fragment = new MessageFragment();

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_message, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		msg_xlv = (XListView) view.findViewById(R.id.msg_message_xlv);

		msg_xlv.setOnItemClickListener(this);
		// msg_xlv.setPullLoadEnable(this);
		msg_xlv.setPullRefreshEnable(this);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		adapter = new MessageAdapter(getActivity());
		msg_xlv.setAdapter(adapter);
		
		msg_xlv.startRefresh();
	}

	private void getData() {

		if (!isLogin()) {
			return;
		}
		// 生源地
		int provinceInt = YiKaoApplication.getOriginZone();
		// 专业类别
		int majorInt = YiKaoApplication.getMajorCategory();

		ArrayRequest<List<Message>> request = new ArrayRequest<List<Message>>(UrlConstants.MESSAGE_LIST + "&province="
				+ provinceInt + "&attr=" + majorInt + "&mid=" + YiKaoApplication.getStudentId(), new Listener<List<Message>>() {

			@Override
			public void onResponse(List<Message> response) {
				msg_xlv.stopRefresh();
				if (response != null) {
					if (response.isEmpty()) {
						msg_xlv.setEmptyView(empty_iv);
					}else {
						adapter.setList(response);
					}
				}
			}
		}, new TypeToken<List<Message>>() {
		}.getType());

		queue.add(request);
		queue.start();
	}

	@Override
	public void onLoadMore() {

	}

	@Override
	public void onRefresh() {
		getData();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		Object object = parent.getItemAtPosition(position);

		if (object != null && object instanceof Message) {
			Message message = (Message) object;
			Intent intent = MessageDetailActivity.newIntent(message.title, UrlConstants.MESSAGE_DETAIL + "&id="
					+ message.id);
			startActivity(intent);
		}
	}

}
