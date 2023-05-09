import { Axios } from "./axios";

export default postSignUp = async ({ signUpDTO, handleUserInfo }) => {
	try {
		const res = await Axios.post("/signup", signUpDTO);
		console.log("res:", res);
	} catch (e) {
		console.log(e);
	}
};
