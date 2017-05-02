## DirectionController

### Android module to add a direction controller with which one can control direction in which an object will move.

### Usage

#### Creating a DirectionController object in Activity file.

```
    DirectionController directionController = new DirectionController(this);
```

#### Showing DirectionController in the activity

```
   directionController.show();
```

#### Setting DirectionChangeListener to the controller. To move when the direction changes.

```
  DirectionController.setOnDirectionChangeListener(new OnDirectionChangeListener() {
        @Override
        public void onDirectionChange(float dx, float dy) {
            //Your Code here
            //dx is change in x coordinate if it you tap on up or down button it is 0. If you tap on right or left it is 1 or -1 respectively
            //dy is change in x coordinate if it you tap on up or down button it is 0. If you tap on right or left it is 1 or -1 respectively
        }
    });
```
### Demo

<img src="https://github.com/Anwesh43/DirectionController/blob/master/demo/directioncontroller.gif" width="300px" height="500px">
