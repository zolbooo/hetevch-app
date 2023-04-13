//
//  Home.swift
//  Hetevch
//
//  Created by Bazarsad Uurdmandakh on 2023.04.07.
//

import SwiftUI

struct HomeView: View {
    
    @State private var isShowingModal = false
    
    var body: some View {
        VStack {
            VStack {
                HStack {
                    Text("Өдрийн мэнд")
                        .font(.custom("Inter-Regular_SemiBold", size:20))
                        .foregroundColor(Color.black)
                    Spacer()
                }.padding(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 0))
                
                HStack {
                    Text("₮5000 / 2 өдөр")
                        .font(.custom("Inter-Regular", size:14))
                        .foregroundColor(.gray)
                    Spacer()
                }
                Spacer()
                
                HStack {
                    Text("Өнөөдрийн төсөв")
                        .font(.custom("Inter-Regular", size:14))
                        .foregroundColor(.gray)
                    Spacer()
                }
                
                HStack {
                    Text("₮2500")
                        .font(.custom("Inter-Regular_Bold", size:60))
                        .onTapGesture{
                            isShowingModal = true
                        }
                        .foregroundColor(Color.black)
                        .sheet(isPresented: $isShowingModal, content: {
                            PlusView(isModalPresented: $isShowingModal)
                        })
                        
                    Spacer()
                }
            }.padding()
                .background(Color.white)
            
            VStack {
                HStack {
                    Text("Зарлага")
                        .font(.custom("Inter-Regular_SemiBold", size:16))
                        .foregroundColor(.white)
                    Spacer()
                }
                .padding(20)
                
                OutcomeCard()
                
            
            }
//            .padding(20)
            .background(Color.black)
            .frame(height:400)
            
        }
        
    }
}

struct Home_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
