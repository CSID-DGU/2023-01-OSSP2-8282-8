import { Axios } from "./axios";

export default postLogin = async (loginDTO, handleUserInfo) => {
	try {
		const res = await Axios.post("/login", loginDTO);
		const data = res.data.data;
		const { accessToken, refreshToken, isSubscribed, userId } = data;
		console.log(accessToken);
		console.log(refreshToken);
		console.log(isSubscribed);
		console.log(userId);
	} catch (e) {
		console.log(e);
	}
};
