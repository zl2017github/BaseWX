package cn.com.et.wx.vo.menu;

/**
 * 复杂按钮（父按钮）
 * @author onetime
 */
public class ComplexButton  extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] subButton) {
		sub_button = subButton;
	}

}
