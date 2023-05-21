import { Axios } from "./axios";


export default getSearch = async (handleContents, type, keyword) => {
	try {
        console.log({type}, {keyword});
		const res = await Axios.get(`/search/${type}s?keyword=${keyword}`);//
		const data = res.data.data;
		const { contentsList } = data;

		handleContents(contentsList);
	} catch (e) {
		console.log(e);
	}
};
