import "react-native-gesture-handler";
import { RecoilRoot } from "recoil";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { NavigationContainer } from "@react-navigation/native";
import SignUp from "./src/pages/SignUp";
import LogIn from "./src/pages/Login";
import MainPage from "./src/pages/MainPage";
import MyPage from "./src/pages/MyPage";
import MyLibrary from "./src/pages/MyLibrary";
import BookDetail from "./src/pages/BookDetail";
import NoteDetail from "./src/pages/NoteDetail";
import MyNotes from "./src/pages/MyNotes";
import SearchResult from "./src/pages/SearchResult";
import ContentsAll from "./src/pages/contentsAll";
import ContentReader from "./src/pages/ContentReader";

const Stack = createNativeStackNavigator();

export default function App() {
	return (
		<RecoilRoot>
			<NavigationContainer>
				<Stack.Navigator screenOptions={{ headerShown: false }}>
					<Stack.Screen name="LogIn" component={LogIn} />
					<Stack.Screen name="SignUp" component={SignUp} />
					<Stack.Screen name="MainPage" component={MainPage} />
					<Stack.Screen name="MyPage" component={MyPage} />
					<Stack.Screen name="MyLibrary" component={MyLibrary} />
					<Stack.Screen name="SearchResult">
						{(props) => <SearchResult {...props} />}
					</Stack.Screen>
					<Stack.Screen name="BookDetail">
						{(props) => <BookDetail {...props} />}
					</Stack.Screen>
					<Stack.Screen name="NoteDetail">
						{(props) => <NoteDetail {...props} />}
					</Stack.Screen>
					<Stack.Screen name="MyNotes" component={MyNotes} />
					<Stack.Screen name="BookAll">
						{(props) => <ContentsAll {...props} />}
					</Stack.Screen>
					<Stack.Screen name="NoteAll">
						{(props) => <ContentsAll {...props} />}
					</Stack.Screen>
					<Stack.Screen name="ContentReader" component={ContentReader} />
				</Stack.Navigator>
			</NavigationContainer>
		</RecoilRoot>
	);
}
