import { AxiosToken } from "./axios";

export default getMyPage = async (userId, handleSubscribeInfo) => {
	try {
		const res = await AxiosToken.get(`/mypage/${userId}`);
		const data = res.data.data;
		handleSubscribeInfo(data.isSubscribed, data.subscribedInfo, {
			joinedDate: data.joinedDate,
			username: data.username,
		});
	} catch (e) {
		console.log(e);
	}
};
