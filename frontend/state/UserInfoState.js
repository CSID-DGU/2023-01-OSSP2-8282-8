import { atom, selector } from "recoil";

export const UserInfoState = atom({
	key: "UserInfoState",
	default: {},
});

export const UserInfoSelector = selector({
	key: "UserInfo",
	get: ({ get }) => {
		return get(UserInfoState);
	},
	set: ({ set }, newValue) => {
		set(UserInfoState, newValue);
	},
});
