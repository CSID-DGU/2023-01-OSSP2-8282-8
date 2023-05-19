import { Axios } from "./axios";

export default getContentsAll = async (userId, type, handleContentsAll) => {
	try {
		const res = await Axios.get(`/mylib/${type}All/${userId}`);

		handleContentsAll(res.data.data[type + "List"]);
	} catch (e) {
		console.log(e);
	}
};
