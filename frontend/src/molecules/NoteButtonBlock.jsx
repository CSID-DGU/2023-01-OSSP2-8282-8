import styled from "styled-components";
import { TouchableOpacity } from "react-native";

const Container = styled.View`
	width: 40%;
	height: 200px;
	display: flex;
	justify-content: flex-end;
	align-items: flex-end;
`;

const StyledBuyButton = styled.View`
	box-sizing: border-box;
	margin: 0 100px;
`;

const ButtonWrapper = styled.View`
	height: 45px;
	width: 170px;
	border-radius: 15px;
	background: #56aaf6;
	align-items: center;
	justify-content: space-around;
	display: flex;
	flex-direction: row;
`;
const PriceWrapper = styled.View`
	height: 30px;
	width: 90px;
	background: white;
	align-items: center;
	justify-content: center;
	margin-left: 4px;
	border-top-left-radius: 8px;
	border-bottom-left-radius: 8px;
`;
const ButtonTypo = styled.Text`
	font-size: 20px;
	color: white;
	margin-top: 8px;
	margin-bottom: 8px;
	margin-left: 10px;
	margin-right: 10px;
`;
const PriceTypo = styled.Text`
	font-size: 18px;
	font-weight: 600;
`;
const BuyButton = ({ press }) => {
	return (
		<StyledBuyButton onPress={press}>
			<ButtonWrapper>
				<PriceWrapper>
					<PriceTypo>1400₩</PriceTypo>
				</PriceWrapper>
				<ButtonTypo>구매</ButtonTypo>
			</ButtonWrapper>
		</StyledBuyButton>
	);
};

const GotoLibraryButton = ({ press }) => {
	return (
		<TouchableOpacity onPress={press}>
			<ButtonWrapper>
				<ButtonTypo>나의 필기로 가기</ButtonTypo>
			</ButtonWrapper>
		</TouchableOpacity>
	);
};

const NoteButtonBlock = ({ isStored, truepress, falsepress }) => {
	return (
		<Container>
			{isStored ? (
				<GotoLibraryButton press={truepress} />
			) : (
				<BuyButton press={falsepress} />
			)}
		</Container>
	);
};

export default NoteButtonBlock;
