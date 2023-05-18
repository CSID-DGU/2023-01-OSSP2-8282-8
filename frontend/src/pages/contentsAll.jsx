import styled from "styled-components";

import CommunityHeader from "../organisms/Header";

import { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import { UserInfoState } from "../../state/UserInfoState";
import ListContainer from "../organisms/ListContainer";
import getContentsAll from "../../api/getContentsAll";

const ContentsAll = ({ navigation, route }) => {
	const userId = useRecoilValue(UserInfoState).userId;

	const [contentsList, setContentsList] = useState([]);
	const handleContents = (contents) => {
		setContentsList(contents);
	};
	const type = route.params.type;
	const length = contentsList.length;
	useEffect(() => {
		getContentsAll(userId, type, handleContents);
	}, []);
	return (
		<>
			<CommunityHeader navigation={navigation} />
			{[...Array(parseInt(length / 5) + 1).keys()].map((i) => (
				<ListContainer
					products={contentsList.slice(5 * i, 5 * (i + 1))}
					type={type}
				/>
			))}
		</>
	);
};

export default ContentsAll;
