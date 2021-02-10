#version 330

layout(location = 0) in vec3 position;

uniform mat4 projectionMatrix;
uniform vec3 translationVector;
uniform mat4 viewMatrix;

void main(void){
  gl_Position = projectionMatrix * viewMatrix  * vec4(position + translationVector, 1.0);
} 
