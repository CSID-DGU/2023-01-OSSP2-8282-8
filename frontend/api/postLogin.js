import AsyncStorage from "@react-native-async-storage/async-storage";
import { Axios } from "./axios";

export default postLogin = async (loginDTO, handleUserInfo) => {
	try {
		const res = await Axios.post("/login", loginDTO);
		const data = res.data.data;
		const { accessToken, refreshToken, isSubscribed, userId } = data;
		AsyncStorage.setItem("accessToken", accessToken);
		AsyncStorage.setItem("refreshToken", refreshToken);

		handleUserInfo({
			userId: userId,
			isSubscribed: isSubscribed,
		});
	} catch (e) {
		console.log(e);
	}
};
