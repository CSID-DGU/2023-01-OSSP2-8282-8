import { Axios } from "./axios";

export default getBookDetail = async (bookId, highlighted) => {
	try {
		const res = await Axios.post(`/note/metadata/${bookId}`, highlighted);
		const metadata = res.data.metadata;
		const rownums = res.data.pageRowNumbers;
		return { metadatas: metadata, rowNums: rownums };
	} catch (e) {
		console.log(e);
	}
};
