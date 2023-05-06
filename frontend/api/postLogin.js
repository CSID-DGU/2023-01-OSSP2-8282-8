import { Axios } from "./axios";

export default postLogin = async ({ loginDTO, handleUserInfo }) => {
	try {
		const res = await Axios.post("/api/login", loginDTO);
		console.log("res:", res);
	} catch (e) {
		console.log(e);
	}
};
