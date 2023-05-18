import { Axios } from "./axios";
const books = [...Array(10).keys()].map((id) => {
	return {
		id: id + 1,
		name: `운영체제${id + 1}`,
		image: "https://image.yes24.com/goods/89496122/XL",
	};
});//api적용시 삭제

const notes = [...Array(10).keys()].map((id) => {
	return {
		id: id + 1,
		name: `필기자료${id + 1}`,
		image:
			"https://simage.mujikorea.net/goods/31/11/79/07/4550002435097_N_N_400.jpg",
	};
});//api적용시 삭제

export default getMain = async (handleContents) => {
	try {
		const res = await Axios.get(`/main`);
		const data = res.data.data;
		const { newBooks, newNotes } = data;

		handleContents(newBooks, newNotes);
	} catch (e) {
		console.log(e);
	}
};