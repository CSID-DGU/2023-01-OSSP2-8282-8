import styled from "styled-components";
import { TouchableOpacity } from "react-native";

const ButtonWrapper = styled.View`
	height: 45px;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 15px;
	background: #56aaf6;
	box-sizing: border-box;
	padding: 7px 13px;
	margin: 0 100px;
`;
const ButtonTypo = styled.Text`
	font-size: 16px;
	color: #fff;
	font-weight: 600;
`;
const StoreLibraryButton = ({ press }) => {
	return (
		<TouchableOpacity onPress={press}>
			<ButtonWrapper>
				<ButtonTypo>나의 서재에 담기</ButtonTypo>
			</ButtonWrapper>
		</TouchableOpacity>
	);
};

const GotoLibraryButton = ({ press }) => {
	return (
		<TouchableOpacity onPress={press}>
			<ButtonWrapper>
				<ButtonTypo>나의 서재로 가기</ButtonTypo>
			</ButtonWrapper>
		</TouchableOpacity>
	);
};

const BookButtonBlock = ({ isStored, truepress, falsepress }) => {
	return (
		<>
			{isStored ? (
				<GotoLibraryButton press={truepress} />
			) : (
				<StoreLibraryButton press={falsepress} />
			)}
		</>
	);
};

export default BookButtonBlock;
