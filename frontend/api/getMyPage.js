import { AxiosToken } from "./axios";

export default getMyPage = async (userId) => {
	try {
		console.log("userid: ", userId);
		const res = await AxiosToken.get(`/mypage/${userId}`);
		console.log("res", res);
	} catch (e) {
		console.log(e);
	}
};
