package com.liferay.pizza.conneciton.helper.internal.portlet;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.service.CommerceAddressLocalService;
import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
	property = {
		"javax.portlet.name=" + LoginPortletKeys.FAST_LOGIN,
		"javax.portlet.name=" + LoginPortletKeys.LOGIN,
		"mvc.command.name=/login/create_account",
		"service.ranking:Integer=100"
	},
	service = MVCActionCommand.class
)
public class CreateAccountMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");

		String fullAddress = ParamUtil.getString(actionRequest, "fullAddress");
		String phoneNumber = ParamUtil.getString(actionRequest, "phoneNumber");

		mvcActionCommand.processAction(actionRequest, actionResponse);

		User user =
			_userLocalService.getUserByEmailAddress(themeDisplay.getCompanyId(),
				emailAddress);

		CommerceAccount commerceAccount =
			_commerceAccountLocalService.getPersonalCommerceAccount(
				user.getUserId());

//		_commerceAddressLocalService.addCommerceAddress(
//			AccountEntry.class.getName(),
//			commerceAccount.getCommerceAccountId(), commerceAccount.getName(),
//			StringPool.BLANK, fullAddress, StringPool.BLANK, StringPool.BLANK, city,
//			zip, regionId, country.getCountryId(), StringPool.BLANK, true, true,
//			serviceContext);

	}

	@Reference(
		target = "(component.name=com.liferay.login.web.internal.portlet.action.CreateAccountMVCActionCommand)")
	protected MVCActionCommand mvcActionCommand;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceAddressLocalService _commerceAddressLocalService;
}
