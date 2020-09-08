#RestAPI  
기존에 코드가 문제가 있던게 아님   
주석을 보면 알겠지만, header도 제데로 감  
response.body를 통해서 응답 데이터를 받을  수 있음  
response.toString()을 해서 나오는 url은 header가 붙어서 간 url이 아님    
retorfit을 통해서 보내진 url이 아니라 그냥 https://openapi.naver.com/v1/search/news.xml?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&sort=sim 이 주소인 것  
