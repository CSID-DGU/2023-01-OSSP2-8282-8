import "react-native-gesture-handler";
import { RecoilRoot } from "recoil";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { NavigationContainer } from "@react-navigation/native";
import SignUp from "./src/pages/SignUp";
import LogIn from "./src/pages/Login";
import MainPage from "./src/pages/MainPage";
import MyPage from "./src/pages/MyPage";
import MyLibrary from "./src/pages/MyLibrary";

import SearchResult from "./src/pages/SearchResult";
import BookDetail from "./src/pages/BookDetail";
import NoteDetail from "./src/pages/NoteDetail";
import MyNotes from "./src/pages/MyNotes";

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
					<Stack.Screen name="SearchResult" component={SearchResult} />
					<Stack.Screen name="BookDetail" component={BookDetail} />
					<Stack.Screen name="NoteDetail" component={NoteDetail} />
					<Stack.Screen name="MyNotes" component={MyNotes} />
				</Stack.Navigator>
			</NavigationContainer>
		</RecoilRoot>
	);
}
