import { Axios } from "./axios";

export default getBookDetail = async (highlighted, handleMetadata) => {
	try {
		const res = await Axios.post(`/note/metadata`, highlighted);
		const data = res.data.data;
		// handleMetadata
	} catch (e) {
		console.log(e);
	}
};
