import { Axios } from "./axios";

export default getBookDetail = async (
	bookId,
	highlighted,
	handleMetadata,
	handleRowNums
) => {
	try {
		const res = await Axios.post(`/note/metadata/${bookId}`, highlighted);
		const metadata = res.data.metadata;
		const rownums = res.data.pageRowNumbers;
		handleMetadata(metadata);
		handleRowNums(rownums);
		return { metadatas: metadata, rowNums: rownums };
	} catch (e) {
		console.log(e);
	}
};
