import { AxiosToken } from "./axios";

export default getMyPage = (userId) => {
	try {
		console.log("userid: ", userId);
		const res = AxiosToken.get(`/mypage/${userId}`);
		console.log("res", res);
	} catch (e) {
		console.log(e);
	}
};
