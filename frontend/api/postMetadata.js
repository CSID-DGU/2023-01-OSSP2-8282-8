import { Axios } from "./axios";

export default getBookDetail = async (bookId, highlighted, handleMetadata) => {
	try {
		const res = await Axios.post(`/note/metadata/${bookId}`, highlighted);
		const metadata = res.data.metadata;
		handleMetadata(metadata);
	} catch (e) {
		console.log(e);
	}
};
