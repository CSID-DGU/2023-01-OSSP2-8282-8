import { Axios } from "./axios";

export default getSearch = async (handleContents, type, keyword, userId) => {
	try {
		console.log(`/search/${type}s?keyword=${keyword}`);
		const res = await Axios.get(`/search/${type}s`, {
			params: {
				keyword: keyword,
				userId: userId,
			},
		});
		const data = res.data.data;
		const { contentsList } = data;

		handleContents(contentsList);
	} catch (e) {
		console.log(e);
	}
};
