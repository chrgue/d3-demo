# Demo Project for D3

## Test & Build

Run tests:

'''
mvn test
'''

Package and run:
'''
# build jar
mvn package

# run
java -jar target/d3-demo-0.0.1-SNAPSHOT.jar
'''

## View live demo
This app is deployed on heroku:
[https://d3-demo-app.herokuapp.com/](https://d3-demo-app.herokuapp.com/)


## Explanation
This app generates fake dependencies on startup. The components/services are named "component1", "component2" etc.

You can  view the full dependency graph:

'''
https://d3-demo-app.herokuapp.com/
'''

or view the dependecies of a specific component:

'''
https://d3-demo-app.herokuapp.com/component2
'''


It is possible to regenerate the fake dependencies:
'''
https://d3-demo-app.herokuapp.com/?refresh=1
'''



 

