import AsyncStorage from "@react-native-async-storage/async-storage";
import { Axios } from "./axios";

export default postSignUp = async (signUpDTO, handleUserInfo, navigation) => {
	try {
		const res = await Axios.post("/signup", signUpDTO);
		const data = res.data.data;
		const { accessToken, refreshToken, userId } = data;
		AsyncStorage.setItem("accessToken", accessToken);
		AsyncStorage.setItem("refreshToken", refreshToken);

		handleUserInfo({
			userId: userId,
			isSubscribed: false,
		});

		navigation();
	} catch (e) {
		console.log(e);
	}
};
