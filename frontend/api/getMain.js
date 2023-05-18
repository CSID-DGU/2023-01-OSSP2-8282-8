import { Axios } from "./axios";

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
