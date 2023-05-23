import styled from "styled-components";
import { TouchableOpacity } from "react-native";

const Container = styled.View`
	height: 200px;
	display: flex;
	justify-content: flex-end;
	align-items: flex-end;
`;

const StyledBuyButton = styled.TouchableOpacity``;

const ButtonWrapper = styled.View`
	height: 45px;
	max-width: 210px;
	border-radius: 15px;
	background: #56aaf6;
	align-items: center;
	justify-content: space-around;
	display: flex;
	flex-direction: row;
	box-sizing: border-box;
	padding: 0 6px;
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
	font-size: 18px;
	color: #fff;
	font-weight: 600;
	margin-top: 8px;
	margin-bottom: 8px;
	margin-left: 10px;
	margin-right: 10px;
`;
const PriceTypo = styled.Text`
	font-size: 18px;
	font-weight: 600;
`;
const BuyButton = ({ press, price }) => {
	return (
		<StyledBuyButton onPress={press}>
			<ButtonWrapper>
				<PriceWrapper>
					<PriceTypo>{price}₩</PriceTypo>
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
				<ButtonTypo>나의 서재에서 확인하기</ButtonTypo>
			</ButtonWrapper>
		</TouchableOpacity>
	);
};

const NoteButtonBlock = ({ isStored, truepress, falsepress, price }) => {
	return (
		<Container>
			{isStored ? (
				<GotoLibraryButton press={truepress} />
			) : (
				<BuyButton press={falsepress} price={price} />
			)}
		</Container>
	);
};

export default NoteButtonBlock;
