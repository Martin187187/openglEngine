#version 330

layout(location = 0) in vec2 position;
layout(location = 1) in vec2 textureCoords;

uniform mat4 transformationMatrix;

out vec2 pass_textureCoords;


void main(void){
  pass_textureCoords = textureCoords;
  gl_Position = transformationMatrix * vec4( vec2((position.x+1.0)/2.0, (position.y+1.0)/2.0), 0.0, 1.0)
  +transformationMatrix * vec4(0.0,-1.0, 0.0, 0.0);
}