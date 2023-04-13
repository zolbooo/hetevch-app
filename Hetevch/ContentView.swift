//
//  ContentView.swift
//  Hetevch
//
//  Created by Bazarsad Uurdmandakh on 2023.04.07.
//

import SwiftUI

struct ContentView: View {
    @State private var selectedTab = 0
    @State private var isShowingModal = false
    
    init() {
        UITabBar.appearance().backgroundColor = UIColor.white
    }
    
    var body: some View {
            VStack {
                TabView(selection: $selectedTab) {
                    HomeView()
                        .tabItem {
                            VStack {
                                Image(systemName: "house")
                                Text("Нүүр")
                                    .font(.custom("Inter_Regular", size:10))
                            }
                            
                        }.tag(0)
                    
                        
                    
                    GearView()
                        .background(Color.white)
                        .tabItem {
                            Image(systemName: "gear")
                        }.tag(1)
//                        .padding()
                }
                .onAppear{
                    selectedTab = 0
                   
                }
                .accentColor(.black)
            }
//            .padding()
//            VStack {
//                Spacer()
//                CustomTabBar(selectedTab: $tabSelected)
//            }
        
    }
}



struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
