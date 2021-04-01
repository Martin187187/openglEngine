#version 330

layout(location = 0) in vec3 position;
layout(location = 1) in vec3 normal;

out vec3 surfaceNormal;
out vec3 toLightVector;
out vec3 toCameraVector;


uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 lightPosition;


void main(void){

  vec4 worldPosition = transformationMatrix * vec4(position, 1.0);

  gl_Position = projectionMatrix * viewMatrix * worldPosition;

  vec3 actualNormal = normal;
  surfaceNormal = (transformationMatrix * vec4(actualNormal, 0.0)).xyz;
  toLightVector = lightPosition - worldPosition.xyz;
  toCameraVector = (inverse(viewMatrix)* vec4(0.0,0.0,0.0,1.0)).xyz - worldPosition.xyz;
}
