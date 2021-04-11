#version 330

precision mediump float;
in mediump vec3 surfaceNormal;
in mediump vec3 toLightVector;
in mediump vec3 toCameraVector;

out vec4 out_Color;

uniform vec3 lightColour;

uniform float shineDamper;
uniform float reflectivity;

void main(void){

  vec3 unitNormal = normalize(surfaceNormal);
  vec3 unitLightVector = normalize(toLightVector);

  float nDotl = dot(unitNormal, unitLightVector);

  float brightness = max(nDotl, 0.2);
  vec3 diffuse = brightness * lightColour;

  vec3 unitCameraVector = normalize(toCameraVector);
  vec3 lightDirection = -unitLightVector;

  vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);


  float specularFactor = dot(reflectedLightDirection, unitCameraVector);
  specularFactor = max(specularFactor, 0.0);
  float dampedFactor = pow(specularFactor, shineDamper);

  vec3 finalSpecular = dampedFactor * reflectivity * lightColour;


  out_Color = vec4(diffuse,1.0) + vec4(finalSpecular,1.0);
}