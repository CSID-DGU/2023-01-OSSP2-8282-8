import styled from "styled-components";

const Route = styled.TouchableOpacity`
	box-sizing: border-box;
	margin: 20px;
`;

const RouteText = styled.Text`
	font-size: 20px;
`;

const CommunityRoute = ({ typo, navigation }) => {
	const path = typo === "로그인" ? "LogIn" : "SignUp";
	const routeOnClick = () => {
		navigation.navigate(path);
	};

	return (
		<Route onPress={routeOnClick}>
			<RouteText>{typo}</RouteText>
		</Route>
	);
};

export default CommunityRoute;
