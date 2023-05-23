import { Axios } from "./axios";

export default postBuyNote = async (buyNoteDTO, handleBuyNote) => {
	try {
		const res = await Axios.post("/note/buy", buyNoteDTO);
		const data = res.data.data;
		handleBuyNote();
	} catch (e) {
		console.log(e);
	}
};
