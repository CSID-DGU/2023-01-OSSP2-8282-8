import { Axios } from "./axios";

export default getBookDetail = async (bookId, highlighted, handleMetadata) => {
	try {
		const res = await Axios.post(`/note/metadata/${bookId}`, highlighted);
		const data = res.data.data;
		console.log("data", data);
		handleMetadata(data);
	} catch (e) {
		console.log(e);
	}
};
