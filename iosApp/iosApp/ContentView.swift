import SwiftUI
import shared

struct ContentView: View {
    @State private var selectedTab = 0
    @State private var isShowingModal = false
    @StateObject var budget = Budget()
    
    
    var body: some View {
        VStack {
            TabView(selection: $selectedTab) {
                HomeView(budget: budget)
                    .tabItem {
                        VStack {
                            Image(systemName: "house")
                            Text("Нүүр")
                                .font(.custom("Inter_Regular", size:10))
                        }
                        
                    }.tag(0)
                
                
                
                GearView(budget: budget)
                    .background(Color.white)
                    .tabItem {
                        Image(systemName: "gear")
                        Text("Тохиргоо")
                            .font(.custom("Inter_Regular", size:10))
                    }.tag(1)
                //                        .padding()
            }
            
            .onAppear{
                selectedTab = 0
                
            }
            //                .accentColor(.black)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
